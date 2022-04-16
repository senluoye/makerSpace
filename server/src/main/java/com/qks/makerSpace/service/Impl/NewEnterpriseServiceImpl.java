package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.NewEnterpriseDao;
import com.qks.makerSpace.dao.OldEnterpriseDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.FormDetails;
import com.qks.makerSpace.entity.response.TechnologyApplyingRes;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewEnterpriseServiceImpl implements NewEnterpriseService , Serializable {

    @Value("${web.upload-path}")
    private String uploadPath;

    private final NewEnterpriseDao newEnterpriseDao;
    private final OldEnterpriseDao oldEnterpriseDao;

    public NewEnterpriseServiceImpl(NewEnterpriseDao newEnterpriseDao, OldEnterpriseDao oldEnterpriseDao) {
        this.newEnterpriseDao = newEnterpriseDao;
        this.oldEnterpriseDao = oldEnterpriseDao;
    }

    /**
     *入园申请（含租赁）
     * @param token
     * @param str
     * @param picture
     * @param representCard
     * @param certificate
     * @param intellectualFile
     * @return
     * @throws IOException
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> updateNewEnterprise(String token,
                                           String str,
                                           MultipartFile picture,
                                           MultipartFile representCard,
                                           MultipartFile certificate,
                                           MultipartFile[] intellectualFile) throws Exception {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        User user = newEnterpriseDao.getUserByUserId(userId);
        if (user == null) return  MyResponseUtil.getResultMap(null,-1,"用户不存在");
        if (user.getUserDescribe() == 3) throw new ServiceException("该用户所属类型不为新注册企业或众创空间");
        JSONObject map = JSONObject.parseObject(str);

        News news = NewParserUtils.newsParser(map);
        String creditCode = news.getCreditCode();
        List<String> userIds = newEnterpriseDao.selectUserIdByCreditCode(creditCode);
        if (userIds.size() != 0 && !userIds.get(0).equals(userId))
            throw new ServiceException("该社会信用代码已被其他用户填报");
        List<UserCompany> users = newEnterpriseDao.selectUserCompany(userId);
        if (users.size() != 0) {
            newEnterpriseDao.updateUserCompany(userId, creditCode);
            List<String> auditIds = newEnterpriseDao.selectAuditIdByCreditCode(users.get(0).getCreditCode());
            List<String> newsIds = newEnterpriseDao.selectNewIdByCreditCode(users.get(0).getCreditCode());
            for (String newId : newsIds)
                newEnterpriseDao.updateNewCreditCode(newId, creditCode);
            for (String auditId: auditIds)
                newEnterpriseDao.updateAuditCreditCode(auditId, creditCode);
        }
        else {
            newEnterpriseDao.insertUserCompany(userId, creditCode);
        }
        String nature = news.getNature();
        if (nature.equals("大学生创业企业") || nature.equals("高校教师创业企业")) {
            try {
                String newName = FileUtils.upload(certificate, uploadPath);
                news.setCertificate(newName);
            } catch (Exception e) {
                throw new ServiceException("请提供与拟注册企业性质对应的文件");
            }
        }
        news.setPicture(FileUtils.upload(picture,uploadPath));
        news.setRepresentCard(FileUtils.upload(representCard,uploadPath));
        news.setState("未审核");
        NewDemand newDemand = NewParserUtils.newDemandParser(map.getString("newDemand"));
        List<NewShareholder> newShareholders = NewParserUtils.NewShareholdersParser(map.getJSONArray("newShareholder"));
        List<NewMainPerson> newMainPeople = NewParserUtils.NewMainPersonParser(map.getJSONArray("newMainPerson"));
        List<NewProject> newProjects = NewParserUtils.NewProjectParser(map.getJSONArray("newProject"));
        List<NewIntellectual> newIntellectuals = NewParserUtils.NewIntellectualParser(map.getJSONArray("newIntellectual"));
        news.setNewDemandId(newDemand.getNewDemandId());
        if (newShareholders.size() != 0)
            news.setNewShareholderId(newShareholders.get(0).getNewShareholderId());
        if (newMainPeople.size() != 0)
            news.setNewMainpersonId(newMainPeople.get(0).getNewMainpersonId());
        if (newProjects.size() != 0)
            news.setNewProjectId(newProjects.get(0).getNewProjectId());
        if (newIntellectuals.size() != 0) {
            news.setNewIntellectualId(newIntellectuals.get(0).getNewIntellectualId());
            if (newIntellectuals.size() != intellectualFile.length){
                return MyResponseUtil.getResultMap(null,-1,"知识产权数量不足");

            }
            for (int i = 0; i < newIntellectuals.size(); i++) {
                String newName = FileUtils.upload(intellectualFile[i],uploadPath);
                newIntellectuals.get(i).setIntellectualFile(newName);
            }
        }
        String time = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
        news.setSubmitTime(time);

        if (newEnterpriseDao.insertNew(news) <= 0)
            throw new ServiceException("信息插入失败：news");

        if (newEnterpriseDao.addNewDemand(newDemand) <= 0)
            throw new ServiceException("信息插入失败：房租");
        for (NewMainPerson newMainPerson : newMainPeople) {
            if (newEnterpriseDao.insertNewMainPerson(newMainPerson) <= 0)
                throw new ServiceException("信息插入失败：主要成员");
        }
        for (NewProject newProject : newProjects) {
            if (newEnterpriseDao.insertNewProject(newProject) <= 0)
                throw new ServiceException("信息插入失败：科技项目");
        }
        for (NewShareholder newShareholder : newShareholders) {
            if (newEnterpriseDao.insertNewShareholder(newShareholder) <= 0) {
                throw new ServiceException("信息插入失败：股东");
            }
        }
        for (NewIntellectual newIntellectual : newIntellectuals) {
            if (newEnterpriseDao.insertNewIntellectual(newIntellectual) <= 0)
                throw new ServiceException("信息插入失败：知识产权");
        }
        Audit audit = new Audit();
        audit.setAuditId(UUID.randomUUID().toString());
        audit.setCreditCode(creditCode);
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");
        if (userDescribe.equals("2") || userDescribe.equals("3"))
            audit.setDescribe("科技园");
        else
            audit.setDescribe("众创空间");
        audit.setSubmitTime(time);

        if (newEnterpriseDao.insertAudit(audit) <= 0)
            throw new ServiceException("信息插入失败：audit");

        Map<String, Object> forMap = new HashMap<>();
        forMap.put("creditCode",creditCode);
        return MyResponseUtil.getResultMap(forMap,0,"success");
    }



    /**
     * 获取上一次入园申请
     * @return
     */
    @Override
    public Map<String, Object> getNewEnterprise(String id) {

        News news = newEnterpriseDao.getNew(id);

        List<NewDemand> newDemands = newEnterpriseDao.getNewDemandById(news.getNewDemandId());
        List<NewMainPerson> newMainPeople = newEnterpriseDao.getNewMainPeopleById(news.getNewMainpersonId());
        List<NewProject> newProjects = newEnterpriseDao.getNewProjectById(news.getNewProjectId());
        List<NewIntellectual> newIntellectuals = newEnterpriseDao.getNewIntellectualById(news.getNewIntellectualId());
        List<NewShareholder> newShareholders = newEnterpriseDao.getNewShareholderById(news.getNewShareholderId());

        Map<String, Object> temp = NewParserUtils.NewGetResponse(news);

        temp.put("newDemand",newDemands);
        temp.put("newMainPerson",newMainPeople);
        temp.put("newProject",newProjects);
        temp.put("newIntellectual",newIntellectuals);
        temp.put("newShareholder",newShareholders);

        return MyResponseUtil.getResultMap(temp,0,"success");

    }

    /**
     * 获取某个企业的所有季度报表
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getFormByCreditCode(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = newEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) throw new ServiceException("您并没有填写入园申请表");

        String creditCode = creditCodes.get(0);
        List<FormDetails> data = newEnterpriseDao.getAllFormDetails(creditCode);

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 新企业续约
     * @param token
     * @param file
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    @Override
    public Map<String, Object> newEnterpriseContract(String token, MultipartFile file) throws ServiceException, IOException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = newEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) throw new ServiceException("您还没有申请账号");
        List<String> oldIdList = newEnterpriseDao.getNewIdList(creditCodes.get(0));
        if (oldIdList.size() == 0) throw new ServiceException("您还没有填写入驻申请表");

        String creditCode = creditCodes.get(0);
        String submitTime = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date());

        Contract contract = new Contract();
        contract.setContractId(UUID.randomUUID().toString());
        contract.setCreditCode(creditCode);
        String voucherName = FileUtils.upload(file, uploadPath);
        contract.setVoucher(voucherName);
        contract.setSubmitTime(submitTime);

        if (oldEnterpriseDao.addContract(contract) < 1) throw new ServiceException("上传缴费凭证失败，请重新上传");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 获取以往缴费信息
     * @param token
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getNewEnterpriseContract(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = newEnterpriseDao.selectCreditCodeByUserId(userId);
        if (creditCodes.size() == 0) throw new ServiceException("您并没有填写入驻申请表");

        String creditCode = creditCodes.get(0);
        List<Contract> data = newEnterpriseDao.getNewContractList(creditCode);

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 获取以往所有入园申请记录
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getNewEnterpriseApplying(String token) {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<String> creditCodes = newEnterpriseDao.selectCreditCodeByUserId(userId);
        String creditCode;

        if (creditCodes.size() != 0) {
            creditCode = creditCodes.get(0);
            List<TechnologyApplyingRes> technologyApplyingRes = newEnterpriseDao.selectAuditByCreditCode(creditCode);
            for (TechnologyApplyingRes i : technologyApplyingRes) {
                String submitTime = i.getSubmitTime();
                String id = newEnterpriseDao.getNewIdByCreditCodeAndTime(creditCode, submitTime);
                String name = newEnterpriseDao.getNewNameByCreditCodeAndTime(creditCode, submitTime);
                String suggestion = newEnterpriseDao.getNewSuggestionByCreditCodeAndTime(creditCode, submitTime);
                String note = newEnterpriseDao.getNewNoteByCreditCodeAndTime(creditCode, submitTime);
                i.setName(name);
                i.setSuggestion(suggestion);
                i.setId(id);
                i.setNote(note);
            }
            return MyResponseUtil.getResultMap(technologyApplyingRes,0,"success");
        }
        return MyResponseUtil.getResultMap(null,0,"success");
    }
}