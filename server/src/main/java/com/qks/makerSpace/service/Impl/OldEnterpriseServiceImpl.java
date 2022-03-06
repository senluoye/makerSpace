package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.OldEnterpriseService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.OldParserUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class  OldEnterpriseServiceImpl implements OldEnterpriseService, Serializable {

    private final OldEnterpriseDao oldEnterpriseDao;

    public OldEnterpriseServiceImpl(OldEnterpriseDao oldEnterpriseDao) {
        this.oldEnterpriseDao = oldEnterpriseDao;
    }

    /**
     * 入园申请表填写(含更新)
     * @param token
     * @param str
     * @param license
     * @param certificate
     * @param intellectualFile
     * @param representFile
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> updateOldEnterprise(String token,
                                                   String str,
                                                   MultipartFile license,
                                                   MultipartFile certificate,
                                                   MultipartFile[] intellectualFile,
                                                   MultipartFile representFile) throws Exception {
        /**
         * 首先验证用户是否存在
         */
        String userId = JWTUtils.parser(token).get("userId").toString();
        User user = oldEnterpriseDao.getUserByUserId(userId);
        if (user == null) return MyResponseUtil.getResultMap(null, -1, "用户不存在");

        /**
         * 提取数据
         */
        JSONObject map = JSONObject.parseObject(str);

        // 主表
        Old old = OldParserUtils.parser(map);
        // 租赁
        OldDemand oldDemand = JSONObject.parseObject(String.valueOf(map.getJSONObject("oldDemand")), OldDemand.class);
        // 股东
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.getJSONArray("oldShareholder"));
        // 主要人员
        List<OldMainPerson> oldMainPeoples =  OldParserUtils.OldMainPersonParser(map.getJSONArray("oldMainPerson"));
        // 项目
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.getJSONArray("oldProject"));
        // 知识产权(非必要)
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.getJSONArray("oldIntellectual"));
        // 项目
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.getJSONArray("oldFunding"));

        // 将子表id填入old表中
        old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        old.setOldMainpersonId(oldMainPeoples.get(0).getOldMainpersonId());
        old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        old.setOldFundingId(oldFundings.get(0).getFundingId());

        // 如果知识产权不为空
        if (oldIntellectuals.size() != 0) {
            // 将知识产权表id放入old类中
            old.setOldIntellectualId(oldIntellectuals.get(0).getOldIntellectualId());
            // 查看文件数量是否正确
            if (oldIntellectuals.size() != intellectualFile.length)
                return MyResponseUtil.getResultMap(null, -1, "知识产权文件数量不足");
            // 将文件放入知识产权类中
            for (int i = 0; i < oldIntellectuals.size(); i++) {
                oldIntellectuals.get(i).setIntellectualFile(intellectualFile[i].getBytes());
            }
        }
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 存放表单提交时间
        old.setSubmitTime(time);

        /**
         * 首先向old表中插入数据
         */
        if (oldEnterpriseDao.insertOld(old) <= 0)
            throw new ServiceException("信息插入失败:old");

        /**
         * 其次向从表中插入数据
         */
        for (OldMainPerson oldMainPeople : oldMainPeoples) {
            if (oldEnterpriseDao.insertOldMainPeople(oldMainPeople) <= 0)
                throw new ServiceException("信息插入失败:oldMainPeople");
        }
        for (OldProject oldProject : oldProjects) {
            if (oldEnterpriseDao.insertOldProjects(oldProject) <= 0)
                throw new ServiceException("信息插入失败:oldProject");
        }
        for (OldIntellectual oldIntellectual : oldIntellectuals) {
            if (oldEnterpriseDao.insertOldIntellects(oldIntellectual) <= 0)
                throw new ServiceException("信息插入失败:oldIntellectual");
        }
        for (OldFunding oldFunding : oldFundings) {
            if (oldEnterpriseDao.insertOldFundings(oldFunding) <= 0)
                throw new ServiceException("信息插入失败:oldFunding");
        }
        for (OldShareholder oldShareholder : oldShareholders) {
            if (oldEnterpriseDao.insertOldShareholder(oldShareholder) <= 0)
                throw new ServiceException("信息插入失败:oldShareholder");
        }

        String creditCode = old.getCreditCode();

        /**
         * 绑定用户和公司
         */
        if (oldEnterpriseDao.selectUserCompany(creditCode) != null) // 如果不为空则更新，为空则插入
            oldEnterpriseDao.updateUserCompany(userId, creditCode);
        else
            oldEnterpriseDao.insertUserCompany(userId, creditCode);

        /**
         * 向入园申请审核表中插入数据
         */
        Audit audit = new Audit();
        audit.setAuditId(UUID.randomUUID().toString());
        audit.setCreditCode(creditCode);
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");
        audit.setDescribe("科技园");
        audit.setSubmitTime(time);

        if (oldEnterpriseDao.insertAudit(audit) <= 0)
            throw new ServiceException("信息插入失败:audit");

        Map<String, Object> forMap = new HashMap<>();
        forMap.put("creditCode",creditCode);
        return MyResponseUtil.getResultMap(forMap, 0, "success");
    }

    /**
     * 信息状态展示
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getOldEnterprise(String token) {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String creditCode = oldEnterpriseDao.selectCreditCodeByUserId(userId);

        List<Map<String, Object>> data = new ArrayList<>();
        List<Old> oldList = oldEnterpriseDao.getAllOld(creditCode);

        oldList.forEach(x -> {
            try {
                data.add(ChangeUtils.getObjectToMap(x));
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        });

        oldList.forEach(x -> {
            List<OldDemand> oldDemands = oldEnterpriseDao.getOldDemandById(x.getOldDemandId());
            List<OldMainPerson> oldMainPeosons = oldEnterpriseDao.getOldMainPeopleById(x.getOldMainpersonId());
            List<OldProject> oldProjects = oldEnterpriseDao.getOldProjectById(x.getOldProjectId());
            List<OldFunding> oldFundings = oldEnterpriseDao.getOldFundingById(x.getOldFundingId());
            List<OldShareholder> oldShareholders = oldEnterpriseDao.getOldShareholderById(x.getOldShareholderId());
            List<OldIntellectual> oldIntellectuals = oldEnterpriseDao.getOldIntellectualById(x.getOldIntellectualId());

            Map<String, Object> temp = OldParserUtils.OldGetResponse(x);
            temp.put("oldDemand", oldDemands);
            temp.put("oldMainPerson", oldMainPeosons);
            temp.put("oldProject", oldProjects);
            temp.put("oldFunding", oldFundings);
            temp.put("oldShareholder", oldShareholders);
            temp.put("oldIntellectual", oldIntellectuals);

            data.add(temp);
        });

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 续约
     * @return Hashmap
     */
    @Override
    public Map<String, Object> oldEnterpriseContract(String json, MultipartFile file) throws ServiceException, IOException {
        JSONObject jsonObject = JSONObject.parseObject(json);
        OldDemand oldDemand = JSONObject.parseObject(json, OldDemand.class);
        String creditCode = jsonObject.getString("creditCode");

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String submitTime = dateFormat.format(new Date());

        String oldDemandId = oldEnterpriseDao.demandExit(creditCode);
        if (oldEnterpriseDao.selectDemandByOldDemandId(oldDemandId).size() == 0)
            throw new ServiceException("请先递交入驻申请书");

        String oldDemandFormId = UUID.randomUUID().toString();
        String id = UUID.randomUUID().toString();
        oldDemand.setId(oldDemandFormId);
        oldDemand.setTime(submitTime);
        oldDemand.setOldDemandId(oldDemandId);

        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            throw new ServiceException("读取文件发生错误，请重新上传");
        }

        if (oldEnterpriseDao.addOldDemand(oldDemand) < 1 &&
                oldEnterpriseDao.addOldDemandContract(id, creditCode, file.getBytes(), submitTime) < 1)
            throw new ServiceException("续约失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 获取某个企业的所有季度报表
     * @param token
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getFormByCreditCode(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String creditCode = oldEnterpriseDao.selectCreditCodeByUserId(userId);

        if (creditCode == null)
            throw new ServiceException("您并没有填写入驻申请表");

        List<FormDetails> data = oldEnterpriseDao.getAllFormDetails(creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }


}

