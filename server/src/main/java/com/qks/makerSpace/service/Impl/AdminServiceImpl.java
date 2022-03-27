package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.entity.request.AdminTechnologyApplyingReq;
import com.qks.makerSpace.entity.request.BriefFormReq;
import com.qks.makerSpace.entity.request.FormReq;
import com.qks.makerSpace.entity.response.*;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public AdminServiceImpl(AdminDao adminDao, JavaMailSender mailSender) {
        this.adminDao = adminDao;
        this.mailSender = mailSender;
    }

    /**
     * 管理员获取用户的账号申请
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getRegister() {
        List<UserAccountApplyingRes> data = adminDao.getUserAccountApplying();
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 管理员分配公司账号
     * @return Hashmap
     */
    @Override
    public Map<String, Object> addNewUser(JSONObject map) throws ServiceException {
        /**
         * 获取公司名称和密码
         */
        String name = map.getString("name");
        String password = map.getString("password");
        String userAccountId = map.getString("userAccountId");

        // 首先看看申请表里有没有这位用户
        UserAccountApplying userAccountApplying = adminDao.getUserFormApplyingByName(name);
        if (userAccountApplying == null) throw new ServiceException("分配的用户信息不存在");

        /**
         * 如果user表中没有该用户，则修改密码并向user表中添加用户，否则报错
         */
        List<User> users = adminDao.getUserByName(name);
        if (users.size() != 0) throw new ServiceException("用户已存在，不需要重新分配");

        User user = new User();
        user.setUserId(userAccountId);
        user.setName(userAccountApplying.getName());
        user.setPassword(password);
        user.setUserDescribe(userAccountApplying.getDescribe());
        user.setEmail(userAccountApplying.getEmail());
        user.setSubmitTime(userAccountApplying.getSubmitTime());
        adminDao.addNewUser(user);

        // 之后删除申请表中对应用户的申请记录
        adminDao.deleteUserAccountApplying(userAccountId);

        String text = "公司名称：" + user.getName() + "\n" + "公司密码：" + user.getPassword();

        /**
         * 最后发送用户邮箱
         */
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());//收信人
            message.setSubject("公司账号与密码");//主题
            message.setText(text);//内容
            message.setFrom(from);//发信人
            mailSender.send(message);
        } catch (Exception e) {
            throw new ServiceException("发送邮件失败");
        }

        user.setPassword(null); // 去掉密码
        return MyResponseUtil.getResultMap(user, 0, "success");

    }

    /**
     * 获取最新所有未审核科技园入园申请
     * @return
     */
    @Override
    public Map<String, Object> getAllTechnologyApplying() {
        List<AdminTechnologyApplyingReq> lists = adminDao.getAllTechnologyApplying();
        for (AdminTechnologyApplyingReq applyingReq : lists) {
            String id;
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = adminDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) { // 不为0，在旧企业中
                id = adminDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = adminDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = adminDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取最新所有已审核科技园入园申请
     * @return
     */
    public Map<String, Object> getAllTechnologyApplied() {
        List<AdminTechnologyApplyingReq> lists = adminDao.getAllApplied();
        for (AdminTechnologyApplyingReq applyingReq : lists) {
            String id;
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = adminDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) { // 不为0，在旧企业中
                id = adminDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = adminDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = adminDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取所有科技园入园申请信息缩略版（包含审核与未审核）
     * @return
     */
    public Map<String, Object> getAllApplying() {
<<<<<<< HEAD
        // 获取科技园表中所有公司的最新入园申请信息
        List<AllTechnologyApplyingRes> lists = adminDao.getTechnologyApplying();

        for (AllTechnologyApplyingRes i : lists) {
            Audit audit = adminDao.getAuditByCreditCode(i.getCreditCode());
            if (audit != null) i.setAdministratorAudit(audit.getAdministratorAudit());
            else i.setAdministratorAudit("未提交");
            i.setDescribe("科技园");
=======
        List<AdminTechnologyApplyingReq> lists = adminDao.getAllApplying();
        for (AdminTechnologyApplyingReq applyingReq : lists) {
            String id;
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = adminDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) { // 不为0，在旧企业中
                id = adminDao.selectOldIdByTimeAndCreditCode(applyingReq.getCreditCode(), applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("3");
                else applyingReq.setDescribe("4");
                applyingReq.setName(oldNameList.get(0));
            } else {
                List<String> newNameList = adminDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                id = adminDao.selectNewIdByTimeAndCreditCode(applyingReq.getCreditCode(),applyingReq.getSubmitTime());
                if (applyingReq.getDescribe().equals("科技园")) applyingReq.setDescribe("2");
                else applyingReq.setDescribe("4");
                applyingReq.setName(newNameList.get(0));
            }
            applyingReq.setId(id);
>>>>>>> 6852f7e0f52a451a282feebb78822297a85b9c8c
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取最新所有未审核众创空间入园申请
     * @return
     */
    @Override
    public Map<String, Object> getAllSpaceApplying() {
        List<AdminSpaceApplyingReq> lists = adminDao.getAllSpaceApplying();
        for (AdminSpaceApplyingReq spaceApplyingReq : lists) {
            System.out.println(spaceApplyingReq);
            String inApplyId =  spaceApplyingReq.getInApplyId();
            String name = adminDao.getSpaceNameByCreditCode(inApplyId).get(0);
            spaceApplyingReq.setName(name);
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取全部科技园企业的部分信息
     */
    @Override
    public Map<String, Object> getAllDetails() {
        List<AllTechnology> data = new ArrayList<>();
        List<AllTechnology> dataOne = adminDao.getAllOldDetails();

        for (AllTechnology allTechnology : dataOne) {
            if (allTechnology.getAlive() == 1) {
                allTechnology.setCompanyKind("old");
                data.add(allTechnology);
            }
        }

        List<AllTechnology> dataTwo = adminDao.getAllNewDetails();
        System.out.println(dataTwo.toString());

        for (AllTechnology allTechnology : dataTwo) {
            if (allTechnology.getAlive() == 1) {
                allTechnology.setCompanyKind("new");
                data.add(allTechnology);
            }
        }
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getOldTechnologyById(String id) throws ServiceException {
        Old old = adminDao.getOld(id);
        if (old == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("old", old);
        data.put("oldDemand", adminDao.getOldDemandById(old.getOldDemandId()));
        data.put("oldShareholder", adminDao.getOldShareholderById(old.getOldShareholderId()));
        data.put("oldMainPerson", adminDao.getOldMainPeopleById(old.getOldMainpersonId()));
        data.put("oldProject", adminDao.getOldProjectById(old.getOldProjectId()));
        data.put("oldIntellectual", adminDao.getOldIntellectualById(old.getOldIntellectualId()));
        data.put("oldFunding", adminDao.getOldFundingById(old.getOldFundingId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个新企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getNewTechnologyById(String id) throws ServiceException {
        News news = adminDao.getNew(id);
        if (news == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

        data.put("news", news);
        data.put("newDemand", adminDao.getNewDemandById(news.getNewDemandId()));
        data.put("newShareholder", adminDao.getNewShareholder(news.getNewShareholderId()));
        data.put("newMainPerson", adminDao.getNewMainPerson(news.getNewMainpersonId()));
        data.put("newProject", adminDao.getNewProject(news.getNewProjectId()));
        data.put("newIntellectual", adminDao.getNewIntellectual(news.getNewIntellectualId()));

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getSpaceById(String inApplyId) throws IllegalAccessException {
        Space space = adminDao.getSpaceById(inApplyId);
        Map<String, Object> data = ChangeUtils.getObjectToMap(space);
        List<SpacePerson> spacePersons = adminDao.getPersonListByInApplyId(inApplyId);
        Audit audit = adminDao.getAuditByCreditCode(inApplyId);
        String administratorAudit = audit.getAdministratorAudit();

        data.put("person", spacePersons);
        data.put("administratorAudit", administratorAudit);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取全部已入园审核众创空间企业部分信息
     * @return
     */
    @Override
    public Map<String, Object> getAllSpaceDetails() {
        List<Space> spaces = adminDao.getAllSpaceDetails();
        List<AllSpace> allSpaces = new ArrayList<>();

        for (Space space : spaces) {
            AllSpace allSpace = new AllSpace();
            String inApplyId = space.getInApplyId();
            Audit audit= adminDao.getAuditByCreditCode(inApplyId);
            List<SpacePerson> spacePeople = adminDao.getSpacePeopleById(inApplyId);

            allSpace.setInApplyId(inApplyId);
            allSpace.setAdministratorAudit(audit.getAdministratorAudit());
            allSpace.setLeadershipAudit(audit.getLeadershipAudit());
            allSpace.setCreateName(space.getCreateName());
            allSpace.setApplyTime(space.getApplyTime());
            allSpace.setTeamNumber(space.getTeamNumber());
            allSpace.setDescribe(space.getDescribe());
            allSpace.setHelp(space.getHelp());
            allSpace.setPerson(spacePeople);

            allSpaces.add(allSpace);
        }

        return MyResponseUtil.getResultMap(allSpaces, 0, "success");
    }

    /**
     * 删除某一个企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteByCreditCode(String creditCode) throws ServiceException {
        if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode).size() == 0)
            throw new ServiceException("表中不存在该项数据");
        else if (adminDao.deleteOldByCreditCode(creditCode) < 1)
                throw new ServiceException("删除失败");

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 删除一个众创空间的申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> deleteSpaceById(String inApplyId) throws ServiceException {
        if (adminDao.deleteSpaceByCreditCode(inApplyId) < 1)
            throw new ServiceException("删除信息失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    /**
     * 同意某一个科技园入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeTechnologyById(JSONObject map) throws ServiceException {
        String id = map.getString("id");
        String creditCode;
        int flag;

        // 首先判断用户是新企业还是旧企业
        if (adminDao.getOldById(id).size() != 0) { // 是旧企业
            creditCode = adminDao.getOldCreditCodeById(id).get(0);
            flag = 0;
        } else if (adminDao.getNewById(id).size() != 0){ // 是新企业
            creditCode = adminDao.getNewCreditCodeById(id).get(0);
            flag = 1;
        } else throw new ServiceException("该企业不存在");

        // 初始化数据
        AdminSuggestion adminSuggestion = new AdminSuggestion();
        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        Audit audit = adminDao.getLastAuditByCreditCode(creditCode);
        if (adminDao.agreeById(audit.getAuditId(), "通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            if (flag == 0) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新失败");
            } else {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新失败");
            }
        }

        return MyResponseUtil.getResultMap(id, 0, "success");
    }
    /**
     * 取消某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException {
        String id = map.getString("id");
        String creditCode;
        int flag;

        // 首先判断用户是新企业还是旧企业
        if (adminDao.getOldById(id).size() != 0) { // 是旧企业
            creditCode = adminDao.getOldCreditCodeById(id).get(0);
            flag = 0;
        } else if (adminDao.getNewById(id).size() != 0){ // 是新企业
            creditCode = adminDao.getNewCreditCodeById(id).get(0);
            flag = 1;
        } else throw new ServiceException("该企业不存在");

        // 初始化数据
        AdminSuggestion adminSuggestion = new AdminSuggestion();
        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        Audit audit = adminDao.getLastAuditByCreditCode(creditCode);
        if (adminDao.agreeById(audit.getAuditId(), "未通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            if (flag == 0) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新失败");
            } else {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新失败");
            }
        }

        return MyResponseUtil.getResultMap(id, 0, "success");
    }

    /**
     * 同意某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> agreeSpaceById(JSONObject map) throws ServiceException {
        String inApplyId = map.getString("inApplyId");
        AdminSpaceSuggestion adminSpaceSuggestion = new AdminSpaceSuggestion();
        adminSpaceSuggestion.setInApplyId(inApplyId);
        adminSpaceSuggestion.setLeaderOpinion(map.getString("officeOpinion"));
        adminSpaceSuggestion.setLeaderOpinion(map.getString("leaderOpinion"));

        // 首先更新audit表
        if (adminDao.agreeById(inApplyId, "通过") < 1)
            throw new ServiceException("管理员审核失败");

        // 然后更新space表
        if (adminDao.updateSpaceBySuggestion(adminSpaceSuggestion) < 1)
            throw new ServiceException("更新意见失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    /**
     * 不同意某一个企业众创空间申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeSpaceById(JSONObject map) throws ServiceException {
        String inApplyId = map.getString("inApplyId");
        AdminSpaceSuggestion adminSpaceSuggestion = new AdminSpaceSuggestion();
        adminSpaceSuggestion.setInApplyId(inApplyId);
        adminSpaceSuggestion.setLeaderOpinion(map.getString("officeOpinion"));
        adminSpaceSuggestion.setLeaderOpinion(map.getString("leaderOpinion"));

        if (adminDao.disagreeById(inApplyId, "不通过") < 1)
            throw new ServiceException("管理员审核失败");

        // 然后更新space表
        if (adminDao.updateSpaceBySuggestion(adminSpaceSuggestion) < 1)
            throw new ServiceException("更新意见失败");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    /**
     * 同意季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> agreeFormById(JSONObject map) throws ServiceException {
        String formId = map.getString("formId");
        if (adminDao.agreeForm(formId) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("操作异常");
    }

    /**
     * 不同意季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> disagreeFormById(JSONObject map) throws ServiceException {
        String formId = map.getString("formId");
        if (adminDao.disagreeForm(formId) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("操作异常");
    }

    //---季度报表从此处---
    /**
     * 获取全部未通过的季度报表
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormToAudit() throws ServiceException {
        List<BriefFormReq> list = adminDao.getDoubleAudit();
        List<BriefFormReq> realList = new ArrayList<>();

        for(BriefFormReq briefFormReq : list) {
            if (briefFormReq.getAlive() == 1) {
                realList.add(briefFormReq);
            }
        }

        if (realList.size() != 0) {
            Iterator<BriefFormReq> iterator = realList.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                if (briefFormReq.getAdminAudit().equals("0")) briefFormReq.setAdminAudit("待审核");
                else briefFormReq.setAdminAudit("未通过");

                if (briefFormReq.getLeaderAudit().equals("0")) briefFormReq.setLeaderAudit("待审核");
                else briefFormReq.setLeaderAudit("未通过");
            }
            return MyResponseUtil.getResultMap(realList,0,"success");
        }
        else throw new ServiceException("没有需要审核的季度报表");



    }

    /**
     * 获取领导未通过的季度报表
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormLeaderAudit() throws ServiceException {
        List<BriefFormReq> list = adminDao.getLeaderAudit();
        List<BriefFormReq> realList = new ArrayList<>();

        for(BriefFormReq briefFormReq : list) {
            if (briefFormReq.getAlive() == 1) {
                realList.add(briefFormReq);
            }
        }

        if (realList.size() != 0){
            Iterator<BriefFormReq> iterator = realList.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                briefFormReq.setAdminAudit("已通过");
                if (briefFormReq.getLeaderAudit().equals("0")) briefFormReq.setLeaderAudit("待审核");
                else briefFormReq.setLeaderAudit("未通过");
            }
            return MyResponseUtil.getResultMap(realList,0,"success");
        }
        else throw new ServiceException("没有需要领导审核的季度报表");
    }

    /**
     * 获取全部已通过的季度报表
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getAudited() throws ServiceException {
        List<BriefFormReq> list = adminDao.getAudited();
        List<BriefFormReq> realList = new ArrayList<>();

        for(BriefFormReq briefFormReq : list) {
            if (briefFormReq.getAlive() == 1) {
                realList.add(briefFormReq);
            }
        }

        if (realList.size() != 0) {
            Iterator<BriefFormReq> iterator = realList.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                briefFormReq.setAdminAudit("已通过");
                briefFormReq.setLeaderAudit("已通过");
            }
            return MyResponseUtil.getResultMap(realList,0,"sucess");
        }
        else throw new ServiceException("没有通过的审核的季度报表");
    }

    /**
     * 获取某个企业最新的季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormDetail(JSONObject map) throws ServiceException {
        String id =map.getString("id");

        FormReq formReq = adminDao.getDetailForm(id);
        if (formReq == null) throw new ServiceException("该数据不存在，请刷新重试");
        else return MyResponseUtil.getResultMap(formReq,0,"success");
    }


    /**
     * 删除企业的季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> deleteForm(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");
        String getTime = map.getString("getTime");

        if (adminDao.deleteForm(creditCode,getTime) > 0) return MyResponseUtil.getResultMap(null,0,"success");
        else throw new ServiceException("删除数据失败");
    }

    /**
     * 获取季度报表所包含的所有年份和季度
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormTimeList() {
        List<String> timeList = adminDao.getTimeList();
        return MyResponseUtil.getResultMap(timeList, 0, "success");
    }

    /**
     * 获取某年某个季度全部季度报表（包含未通过和通过）
     * @param httpServletRequest
     */
    @Override
    public Map<String, Object> getFormList(JSONObject map) {
        String year = map.getString("year");
        String quarter = map.getString("quarter");
        List<Form> data = adminDao.getFormListByTime(year, quarter);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某个企业的历史季度报表
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> getFormByCompany(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");
        List<BriefFormReq> list = adminDao.getCompanyForm(creditCode);
        if (list.size() != 0) {
            Iterator<BriefFormReq> iterator = list.iterator();
            while (iterator.hasNext()) {
                BriefFormReq briefFormReq = iterator.next();
                if (briefFormReq.getAdminAudit().equals("0")) briefFormReq.setAdminAudit("待审核");
                else if (briefFormReq.getAdminAudit().equals("1")) briefFormReq.setAdminAudit("未通过");
                else briefFormReq.setAdminAudit("已通过");

                if (briefFormReq.getLeaderAudit().equals("0")) briefFormReq.setLeaderAudit("待审核");
                else if (briefFormReq.getLeaderAudit().equals("1")) briefFormReq.setLeaderAudit("未通过");
                else briefFormReq.setLeaderAudit("已通过");
            }
            return MyResponseUtil.getResultMap(list,0,"success");
        } else throw new ServiceException("该企业未提交过季度报表");
    }

    /**
     * 获取导出表的信息
     * @return HashMap
     */
    @Override
    public Map<String, Object> getDownLoadForm () {
        return null;
    }

    /**
     * 导出文件
     * @param
     * @param
     */
    @Override
    public void downLoadWord(HttpServletResponse response, Map<String, Object> map) throws ServiceException {
        try {
            String fileName = Calendar.getInstance().get(Calendar.YEAR) + "年度" + map.get("teamName").toString() + "统计表";
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"",fileName+".docx"));
            WordChangeUtils.searchAndReplace(response.getOutputStream(), map);
        } catch (IOException e) {
            throw new ServiceException("导出信息表失败");
        }
    }
}