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
     * 注册
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldRegister(JSONObject map) throws ServiceException {
        Old old = JSONObject.parseObject(String.valueOf(map), Old.class);
        System.out.println(old);
        if (oldEnterpriseDao.exit(old.getCreditCode()) != null){
            // 之前已经申请
            oldEnterpriseDao.updateOldRegister(old);
        } else {
            // 之前没有申请
            old.setOldId(UUID.randomUUID().toString());
            oldEnterpriseDao.oldRegister(old);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("id", old.getCreditCode());
        return MyResponseUtil.getResultMap(data, 0, "success");

    }

    /**
     * 信息状态展示
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterprise() {
        List<Map<String, Object>> data = new ArrayList<>();
        List<Old> oldList = oldEnterpriseDao.getAllOld();

        oldList.forEach(x -> {
            try {
                data.add(ChangeUtils.getObjectToMap(x));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
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
     * 缴费
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldEnterprisePay(Map<String, Object> map) {
        return null;
    }

    /**
     * 旧企业科技园场地申请
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> oldEnterpriseDemand(JSONObject map) throws ServiceException {
        OldDemand oldDemand = JSONObject.parseObject(String.valueOf(map), OldDemand.class);

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String submitTime = dateFormat.format(new Date());
        String room = map.getString("floor") + " - " + map.getString("position");
        String creditCode = map.getString("creditCode");

        if (oldEnterpriseDao.demandExit(creditCode) != null) {
            // 已经存在
            String oldDemandId = oldEnterpriseDao.selectOldDemandIdByCreditCode(creditCode);
            if (oldEnterpriseDao.updateOldDemand(oldDemand,oldDemandId) < 1)
                throw new ServiceException("插入数据失败:addOldDemand");
        } else {
            // 不存在
            oldDemand.setOldDemandId(UUID.randomUUID().toString());
            if (oldEnterpriseDao.addOldDemand(oldDemand) < 1
                    || oldEnterpriseDao.updateOldDemandId(creditCode, oldDemand.getOldDemandId()) < 1)
                throw new ServiceException("插入数据失败:addOldDemand");
        }

        // 更新主表中的剩余数据
        if (oldEnterpriseDao.updateOldForDemand(creditCode, "0", submitTime, room, oldDemand.getOldDemandId()) < 1)
            throw new ServiceException("插入数据失败:updateOldForDemand");

        Map<String, Object> data = new HashMap<>();
        data.put("creditCode", creditCode);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 入园申请表填写
     * @param str
     * @return
     */
    @Override
    public Map<String, Object> updateOldEnterprise(String token,
                                                   String str,
                                                   MultipartFile[] files) throws Exception {
        String userId = JWTUtils.parser(token).get("userId").toString();
        JSONObject map = JSONObject.parseObject(str);
        String creditCode = map.get("creditCode").toString();
        Date date = new Date();

        List<Audit> auditList = oldEnterpriseDao.getAudit(creditCode);

        if (auditList.size() != 0)
            if (auditList.get(0).isAdministratorAudit() && auditList.get(0).isLeadershipAudit())
                throw new ServiceException("领导和管理员均已审核通过，无法重新填报申请表");

        // 数据初步处理
        Old old = OldParserUtils.parser(map);
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.getJSONArray("oldShareholder"));
        List<OldMainPerson> oldMainPeoples =  OldParserUtils.OldMainPersonParser(map.getJSONArray("oldMainPerson"));
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.getJSONArray("oldProject"));
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.getJSONArray("oldIntellectual"));
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.getJSONArray("oldFunding"));

        old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        old.setOldMainpersonId(oldMainPeoples.get(0).getOldMainpersonId());
        old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        old.setOldIntellectualId(oldIntellectuals.get(0).getOldIntellectualId());
        old.setOldFundingId(oldFundings.get(0).getFundingId());
        old.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        try {
            old.setLicense(files[0].getBytes());
            old.setCertificate(files[1].getBytes());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ServiceException("上传文件数量不足");
        }

        for (int i = 2; i < files.length; i++)
            oldIntellectuals.get(i - 2).setIntellectualFile(files[i].getBytes());

        if (oldEnterpriseDao.exitMainPerson(creditCode) != null) {
            // 如果之前已经有信息存在 --->删除对应信息
            if (oldEnterpriseDao.deleteOldMainPerson(oldEnterpriseDao.selectOldMainPerson(creditCode)) <= 0)
                throw new ServiceException("删除MainPerson错误");
            if (oldEnterpriseDao.deleteOldProject(oldEnterpriseDao.selectOldProject(creditCode)) <= 0)
                throw new ServiceException("删除Project失败");
            if (oldEnterpriseDao.deleteOldFunding(oldEnterpriseDao.selectOldFunding(creditCode)) <= 0)
                throw new ServiceException("删除Funding失败");
            if (oldEnterpriseDao.deleteOldShareholder(oldEnterpriseDao.selectOldShareholder(creditCode)) <= 0)
                throw new ServiceException("删除Shareholder失败");
            if (oldEnterpriseDao.deleteOldIntellectual(oldEnterpriseDao.selectOldIntellectual(creditCode)) <= 0)
                throw new ServiceException("删除Intellectual失败");

        }

        //更新主表
        if (oldEnterpriseDao.updateOld(old) <= 0)
            throw new ServiceException("信息插入失败:old");
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

        // 插入数据到审核表
        Audit audit = new Audit();
        audit.setAuditId(creditCode);
        audit.setAdministratorAudit(false);
        audit.setLeadershipAudit(false);

        if (oldEnterpriseDao.insertAudit(audit) <= 0)
            throw new ServiceException("信息插入失败:audit");

        // 绑定用户和公司
        if (oldEnterpriseDao.selectUserCompany(creditCode) != null) // 如果不为空则更新，为空则插入
            oldEnterpriseDao.updateUserCompany(userId,creditCode);
        else
            oldEnterpriseDao.insertUserCompany(userId, creditCode);

        Map<String, Object> forMap = new HashMap<>();
        forMap.put("creditCode",creditCode);
        return MyResponseUtil.getResultMap(forMap, 0, "success");
    }

    /**
     * 获取某个企业的所有季度报表
     * @param
     * @return
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

