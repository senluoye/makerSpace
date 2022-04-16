package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.entity.response.TechnologyApplyingRes;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.OldEnterpriseService;
import com.qks.makerSpace.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class  OldEnterpriseServiceImpl implements OldEnterpriseService, Serializable {

    @Value("${web.upload-path}")
    private String uploadPath;

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
        if (user.getUserDescribe() != 3) throw new ServiceException("该用户所属类型不为迁入企业");

        /**
         * 提取数据
         */
        JSONObject map = JSONObject.parseObject(str);

        // 获取old类
        Old old = OldParserUtils.parser(map);
        String creditCode = old.getCreditCode();

        /**
         * 然后验证creditCode是否被其他用户使用
         */
        List<String> userIds = oldEnterpriseDao.selectUserIdByCreditCode(creditCode);
        if (userIds.size() != 0 && !userIds.get(0).equals(userId))
            throw new ServiceException("该社会信用代码已被其他用户填报");

        /**
         * 绑定用户和公司
         */
        List<UserCompany> users = oldEnterpriseDao.selectUserCompany(userId);
        if (users.size() != 0) {
            // 如果不为空则更新，需要更新old表、audit表、user_company表
            oldEnterpriseDao.updateUserCompany(userId, creditCode);
            List<String> auditIds = oldEnterpriseDao.selectAuditIdByCreditCode(users.get(0).getCreditCode());
            List<String> oldIds = oldEnterpriseDao.selectOldIdByCreditCode(users.get(0).getCreditCode());
            for (String oldId : oldIds)
                oldEnterpriseDao.updateOldCreditCode(oldId, creditCode);
            for (String auditId : auditIds)
                oldEnterpriseDao.updateAuditCreditCode(auditId, creditCode);
        }
        else {
            // 为空则插入
            oldEnterpriseDao.insertUserCompany(userId, creditCode);
        }

        try {
            String newName = FileUtils.upload(license, uploadPath);
            old.setLicense(newName);
        } catch (Exception e) {
            throw new ServiceException("营业执照未上传");
        }

        String nature = old.getNature();
        if (nature.equals("大学生创业企业") || nature.equals("高校教师创业企业")) {
            try {
                String newName = FileUtils.upload(certificate, uploadPath);
                old.setCertificate(newName);
            } catch (Exception e) {
                throw new ServiceException("请提供与企业性质对应的文件");
            }
        }
        old.setState("未审核");

        // 租赁
        OldDemand oldDemand = OldParserUtils.OldDemandParser(map.getString("oldDemand"));

        // 股东
        List<OldShareholder> oldShareholders = OldParserUtils.OldShareholderParser(map.getJSONArray("oldShareholder"));

        // 主要人员
        List<OldMainPerson> oldMainPeoples =  OldParserUtils.OldMainPersonParser(map.getJSONArray("oldMainPerson"));

        // 入园项目
        List<OldProject> oldProjects = OldParserUtils.OldProjectsParser(map.getJSONArray("oldProject"));

        // 知识产权(非必要)
        List<OldIntellectual> oldIntellectuals = OldParserUtils.OldIntellectualParser(map.getJSONArray("oldIntellectual"));

        // 承担财政项目
        List<OldFunding> oldFundings = OldParserUtils.OldFundingParser(map.getJSONArray("oldFunding"));

        // 将子表id填入old表中
        if (oldShareholders.size() != 0)
            old.setOldShareholderId(oldShareholders.get(0).getOldShareholderId());
        if (oldMainPeoples.size() != 0)
            old.setOldMainpersonId(oldMainPeoples.get(0).getOldMainpersonId());
        if (oldProjects.size() != 0)
            old.setOldProjectId(oldProjects.get(0).getOldProjectId());
        if (oldFundings.size() != 0)
            old.setOldFundingId(oldFundings.get(0).getFundingId());
        old.setOldDemandId(oldDemand.getOldDemandId());

        // 如果知识产权不为空
        if (oldIntellectuals.size() != 0) {
            // 将知识产权表id放入old类中
            old.setOldIntellectualId(oldIntellectuals.get(0).getOldIntellectualId());
            // 查看文件数量是否正确
            if (oldIntellectuals.size() != intellectualFile.length)
                return MyResponseUtil.getResultMap(null, -1, "知识产权文件数量与实际上传数据不匹配");
            // 将文件放入知识产权类中
            for (int i = 0; i < oldIntellectuals.size(); i++) {
                String newName = FileUtils.upload(intellectualFile[i], uploadPath);
                oldIntellectuals.get(i).setIntellectualFile(newName);
            }
        }

        String time = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
        // 存放表单提交时间
        old.setSubmitTime(time);

        // 上传法人身份证
        String newName = FileUtils.upload(representFile, uploadPath);
        old.setRepresentFile(newName);

        /**
         * 首先向old表中插入数据
         */
        if (oldEnterpriseDao.insertOld(old) <= 0)
            throw new ServiceException("信息插入失败:old");

        /**
         * 其次向从表中插入数据
         */
        if (oldEnterpriseDao.addOldDemand(oldDemand) <= 0)
            throw new ServiceException("信息插入失败:房租");
        for (OldMainPerson oldMainPeople : oldMainPeoples) {
            if (oldEnterpriseDao.insertOldMainPeople(oldMainPeople) <= 0)
                throw new ServiceException("信息插入失败:主要人员");
        }
        for (OldProject oldProject : oldProjects) {
            if (oldEnterpriseDao.insertOldProjects(oldProject) <= 0)
                throw new ServiceException("信息插入失败:科技项目");
        }
        for (OldIntellectual oldIntellectual : oldIntellectuals) {
            if (oldEnterpriseDao.insertOldIntellects(oldIntellectual) <= 0)
                throw new ServiceException("信息插入失败:知识产权");
        }
        for (OldFunding oldFunding : oldFundings) {
            if (oldEnterpriseDao.insertOldFundings(oldFunding) <= 0)
                throw new ServiceException("信息插入失败:承担财政项目");
        }
        for (OldShareholder oldShareholder : oldShareholders) {
            if (oldEnterpriseDao.insertOldShareholder(oldShareholder) <= 0)
                throw new ServiceException("信息插入失败:股东");
        }

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
        forMap.put("oldId", old.getOldId());
        return MyResponseUtil.getResultMap(forMap, 0, "success");
    }

    /**
     * 获取上一次入园申请
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getOldEnterprise(String token)   {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        String creditCode = creditCodes.get(0);

        Old old = oldEnterpriseDao.getOld(creditCode);

        List<OldDemand> oldDemands = oldEnterpriseDao.getOldDemandById(old.getOldDemandId());
        List<OldMainPerson> oldMainPersons = oldEnterpriseDao.getOldMainPeopleById(old.getOldMainpersonId());
        List<OldProject> oldProjects = oldEnterpriseDao.getOldProjectById(old.getOldProjectId());
        List<OldFunding> oldFundings = oldEnterpriseDao.getOldFundingById(old.getOldFundingId());
        List<OldShareholder> oldShareholders = oldEnterpriseDao.getOldShareholderById(old.getOldShareholderId());
        List<OldIntellectual> oldIntellectuals = oldEnterpriseDao.getOldIntellectualById(old.getOldIntellectualId());

        Map<String, Object> temp = OldParserUtils.OldGetResponse(old);

        temp.put("oldDemand", oldDemands);
        temp.put("oldMainPerson", oldMainPersons);
        temp.put("oldProject", oldProjects);
        temp.put("oldFunding", oldFundings);
        temp.put("oldShareholder", oldShareholders);
        temp.put("oldIntellectual", oldIntellectuals);

        return MyResponseUtil.getResultMap(temp, 0, "success");
    }

    /**
     * 获取某一次入园申请
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterpriseById(String token, String oldId) {
        String userId = JWTUtils.parser(token).get("userId").toString();

        Old old = oldEnterpriseDao.getOldByOldId(oldId);

        List<OldDemand> oldDemand = oldEnterpriseDao.getOldDemandById(old.getOldDemandId());
        List<OldFunding> oldFunding = oldEnterpriseDao.getOldFundingById(old.getOldFundingId());
        List<OldIntellectual> oldIntellectual = oldEnterpriseDao.getOldIntellectualById(old.getOldIntellectualId());
        List<OldMainPerson> oldMainPerson = oldEnterpriseDao.getOldMainPeopleById(old.getOldMainpersonId());
        List<OldProject> oldProject = oldEnterpriseDao.getOldProjectById(old.getOldProjectId());
        List<OldShareholder> oldShareholder = oldEnterpriseDao.getOldShareholderById(old.getOldShareholderId());

//        Map<String, Object> temp = OldParserUtils.OldGetResponse(old);

        Map<String, Object> data = new HashMap<>();
        data.put("old", old);
        data.put("oldDemand", oldDemand);
        data.put("oldMainPerson", oldMainPerson);
        data.put("oldProject", oldProject);
        data.put("oldFunding", oldFunding);
        data.put("oldShareholder", oldShareholder);
        data.put("oldIntellectual", oldIntellectual);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取以往所有入园申请记录
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterpriseApplying (String token) {
        /**
         * 首先找到与用户对应的公司代码
         */
        String userId = JWTUtils.parser(token).get("userId").toString();;
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        String creditCode;

        if (creditCodes.size() != 0) {
            creditCode = creditCodes.get(0);
            List<TechnologyApplyingRes> technologyApplyIngResList = oldEnterpriseDao.selectAuditByCreditCode(creditCode);
            for (TechnologyApplyingRes i : technologyApplyIngResList) {
                String submitTime = i.getSubmitTime();
                String id = oldEnterpriseDao.getOldIdByCreditCodeAndTime(creditCode,submitTime);
                String name = oldEnterpriseDao.getOldNameByCreditCodeAndTime(creditCode,submitTime);
                String suggestion = oldEnterpriseDao.getOldSuggestionByCreditCodeAndTime(creditCode,submitTime);
                String note = oldEnterpriseDao.getOldNoteByCreditCodeAndTime(creditCode,submitTime);
                i.setName(name);
                i.setSuggestion(suggestion);
                i.setId(id);
                i.setNote(note);
            }
            return MyResponseUtil.getResultMap(technologyApplyIngResList, 0, "success");
        }
        return MyResponseUtil.getResultMap(new ArrayList<>(), 0, "success");
    }

    /**
     * 续约管理
     * @return Hashmap
     */
    @Override
    public Map<String, Object> oldEnterpriseContract(String token, JSONObject jsonObject) throws ServiceException, IOException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) {
            throw new ServiceException("您还没有申请账号");
        }

        return MyResponseUtil.getResultMap(creditCodes.get(0), 0, "success");
    }

    /**
     * 缴费管理
     * @param JSONObject, MultipartFile
     * @return map
     */
    @Override
    public Map<String, Object> oldEnterpriseAmount(String token, JSONObject jsonObject, MultipartFile voucher) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) {
            throw new ServiceException("您还没有申请账号");
        }

        Contract contract = new Contract();
        try {
            contract.setContractId(UUID.randomUUID().toString());
            contract.setAmount(Integer.parseInt(jsonObject.getString("amount")));
            contract.setQuarter(Integer.parseInt(jsonObject.getString("quarter")));
            contract.setDescribe(jsonObject.getString("describe"));
            contract.setVoucher(FileUtils.upload(voucher, uploadPath));
            contract.setCreditCode(creditCodes.get(0));
            contract.setSubmitTime(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
        } catch (Exception e) {
            throw new ServiceException("请传递正确的数据格式");
        }

        if (oldEnterpriseDao.addContract(contract) < 1) {
            throw new ServiceException("上传数据失败，请重新上传");
        }

        return MyResponseUtil.getResultMap(creditCodes.get(0), 0, "success");
    }

    /**
     * 获取以往缴费信息
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getOldEnterpriseContract(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) throw new ServiceException("您并没有填写入驻申请表");

        String creditCode = creditCodes.get(0);
        List<Contract> data = oldEnterpriseDao.getOldContractList(creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }



    /**
     * 获取某个企业的所有季度报表
     * @param token
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getFormByCreditCode(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = oldEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) throw new ServiceException("您并没有填写入驻申请表");

        String creditCode = creditCodes.get(0);
        List<FormDetails> data = oldEnterpriseDao.getAllFormDetails(creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }


}

