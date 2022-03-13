package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.entity.request.AdminTechnologyApplyingReq;
import com.qks.makerSpace.entity.response.AdminSpaceSuggestion;
import com.qks.makerSpace.entity.response.AdminSuggestion;
import com.qks.makerSpace.entity.response.AllSpace;
import com.qks.makerSpace.entity.response.AllTechnology;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.ChangeUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final JavaMailSender mailSender;

    private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${spring.mail.username}")
    private String from;

    public AdminServiceImpl(AdminDao adminDao, JavaMailSender mailSender) {
        this.adminDao = adminDao;
        this.mailSender = mailSender;
    }

    /**
     * 管理员分配公司账号
     * @return Hashmap
     */
    @Override
    public Map<String, Object> addNewUser(JSONObject map) {
        /**
         * 获取公司名称和密码
         */
        String name = map.getString("name");
        String password = map.getString("password");
        String email = map.getString("email");
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(name);
        user.setPassword(password);
        user.setUserDescribe(2);
        user.setEmail(email);

        /**
         * 如果用户存在，则修改密码，否则增加新用户
         */
        List<User> users = adminDao.getUserByName(name);
        if (users.size() == 0) {
            adminDao.addNewUser(user);
        } else {
            user.setUserId(users.get(0).getUserId());
            adminDao.UpdateUser(user);
        }

        String text = "公司名称：" + user.getName() + "\n" + "公司密码：" + user.getPassword();

        /**
         * 发送邮箱
         */
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());//收信人
        message.setSubject("公司账号密码");//主题
        message.setText(text);//内容
        message.setFrom(from);//发信人
        mailSender.send(message);

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
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = adminDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) // 不为0，在旧企业中
                applyingReq.setName(oldNameList.get(0));
            else {
                List<String> newNameList = adminDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                applyingReq.setName(newNameList.get(0));
            }
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
        List<AllTechnology> dataOne = adminDao.getAllOldDetails();
//        System.out.println(dataOne.toString());

        Iterator<AllTechnology> iterator_one = dataOne.iterator();
        while (iterator_one.hasNext()) {
            AllTechnology allTechnology = iterator_one.next();
            allTechnology.setCompanyKind("old");
        }

        List<AllTechnology> dataTwo = adminDao.getAllNewDetails();
        System.out.println(dataTwo.toString());
        Iterator<AllTechnology> iterator_two = dataTwo.iterator();
        while (iterator_two.hasNext()) {
            AllTechnology allTechnology = iterator_two.next();
            allTechnology.setCompanyKind("new");
        }

        List<AllTechnology> data = new ArrayList<>(dataOne);
        data.addAll(dataTwo);


        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> getOldTechnologyById(String creditCode) throws ServiceException {
        Old old = adminDao.getOld(creditCode);
        if (old == null)
            throw new ServiceException("数据不存在");

        Map<String, Object> data = new HashMap<>();

//        data.put("creditCode", old.getCreditCode());
//        data.put("registerAddress", old.getRegisterAddress());
//        data.put("registerCapital", old.getRealCapital());
//        data.put("realAddress", old.getRealAddress());
//        data.put("realCapital", old.getRealCapital());
//        data.put("lastIncome", old.getLastIncome());
//        data.put("lastTax", old.getLastTax());
//        data.put("employees", old.getEmployees());
//        data.put("originNumber", old.getOriginNumber());
//        data.put("setDate", old.getSetDate());
//        data.put("nature", old.getNature());
//        data.put("involved", old.getInvolved());
//        data.put("mainBusiness", old.getMainBusiness());
//        data.put("way", old.getWay());
//        data.put("business", old.getBusiness());

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
    public Map<String, Object> getNewTechnologyById(String creditCode) throws ServiceException {
        News news = adminDao.getNew(creditCode);
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
        Audit audit = adminDao.getAuditById(inApplyId);
        String administratorAudit = audit.getAdministratorAudit();

        data.put("person", spacePersons);
        data.put("administratorAudit", administratorAudit);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 获取全部众创空间企业的申请信息
     */
    @Override
    public Map<String, Object> getAllSpaceDetails() {
        List<Space> spaces = adminDao.getAllSpaceDetails();
        List<AllSpace> allSpaces = new ArrayList<>();
        System.out.println(spaces);
        for (Space space : spaces) {
            AllSpace allSpace = new AllSpace();
            Audit audit= adminDao.getAuditById(space.getInApplyId());
            String inApplyId = space.getInApplyId();
            List<SpacePerson> spacePeople = adminDao.getSpacePeopleById(inApplyId);

            allSpace.setInApplyId(inApplyId);
            allSpace.setAdministratorAudit(audit.getAdministratorAudit());
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
        String creditCode = map.getString("creditCode");

        AdminSuggestion adminSuggestion = new AdminSuggestion();
        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        if (adminDao.agreeById(creditCode, "通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            // 更新old/new表中的剩余两个字段
            if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode).size() != 0) {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新new失败");
            } else if (adminDao.selectCreditCodeFromOldByCreditCode(creditCode).size() != 0) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新old失败");
            }
            else throw new ServiceException("表中不存在该信息");
        }

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
    }

    /**
     * 取消某一个企业科技园申请
     * @return HashMap
     */
    @Override
    public Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        AdminSuggestion adminSuggestion = new AdminSuggestion();

        adminSuggestion.setCreditCode(creditCode);
        adminSuggestion.setSuggestion(map.getString("suggestion"));
        adminSuggestion.setNote(map.getString("note"));

        if (adminDao.disagreeById(creditCode, "不通过") < 1) {
            throw new ServiceException("管理员审核失败");
        } else {
            if (adminDao.selectCreditCodeFromNewByCreditCode(creditCode) != null) {
                if (adminDao.updateNewSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新new失败");
            }
            else if (adminDao.selectCreditCodeFromOldByCreditCode(creditCode) != null) {
                if (adminDao.updateOldSuggestion(adminSuggestion) < 0)
                    throw new ServiceException("更新old失败");
            }
            else throw new ServiceException("表中不存在该信息");
        }

        return MyResponseUtil.getResultMap(creditCode, 0, "success");
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
     * 科技园企业季度报表审核通过
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> agreeTechnologyFormById(JSONObject map) throws ServiceException {
        Map<String, Object> data = new HashMap<>();
        String creditCode = map.getString("creditCode");



        return MyResponseUtil.getResultMap(data, 0, "success");
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