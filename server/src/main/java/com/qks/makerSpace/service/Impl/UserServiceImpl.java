package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.response.HomePageRes;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 用户获取主页信息
     * @return
     */
    @Override
    public Map<String, Object> getHomepage(String token) {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<User> users = userDao.getUserByUserId(userId);
        if (users.size() < 1)
            return MyResponseUtil.getResultMap(null, -1, "该账号还未向管理员提交账号申请");

        User user = users.get(0);
        HomePageRes homePageRes = new HomePageRes();
        homePageRes.setDescribe(user.getUserDescribe());

        // 先判断之前有没有提交过入园申请
        List<UserCompany> userCompany = userDao.getUserCompanyByUserId(userId);
        if (userCompany.size() == 0) { // 如果没有填写过入园申请
            homePageRes.setName(user.getName());
            homePageRes.setDemandTime("");
            homePageRes.setSubmitTime("");
            homePageRes.setEnter("未递交申请书");
            return MyResponseUtil.getResultMap(homePageRes, 0, "success");
        }

        // 之前已经提交过入园申请
        String creditCode = userCompany.get(0).getCreditCode();
        if (user.getUserDescribe() == 2 || user.getUserDescribe() == 4) {
            // 如果用户是new 或者 space
            News news = userDao.getLastNewByCreditCode(creditCode);
            homePageRes.setSubmitTime(news.getSubmitTime());
            homePageRes.setName(news.getName());
        } else if (user.getUserDescribe() == 3) {
            // 如果用户是old
            Old old = userDao.getLastOldByCreditCode(creditCode);
            homePageRes.setSubmitTime(old.getSubmitTime());
            homePageRes.setName(old.getName());
        } else {
            homePageRes.setName(user.getName());
            homePageRes.setSubmitTime(null);
            homePageRes.setDemandTime(null);
        }

        String lastSubmitTime = userDao.getLastSubmitTimeByCreditCode(creditCode);
        homePageRes.setDemandTime(lastSubmitTime);
        homePageRes.setEnter("已递交申请书但未入园");
        List<Audit> auditList = userDao.getAuditByCreditCode(creditCode);
        for (Audit audit : auditList) {
            if (audit.getLeadershipAudit().equals("通过")) {
                homePageRes.setEnter("已成功入园");
                break;
            }
        }

        return MyResponseUtil.getResultMap(homePageRes, 0, null);
    }

    /**
     * 管理员修改邮箱
     * @param token
     * @param json
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> changeAdminEmail(String token, JSONObject json) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<User> users = userDao.getUserByUserId(userId);
        if (!users.get(0).getUserId().equals("1"))
            throw new ServiceException("请求主体不为管理员");

        String email = json.getString("email");

        EmailAuth emailAuth = new EmailAuth();
        emailAuth.setEmailAuthId(UUID.randomUUID().toString());
        emailAuth.setUserId(userId);
        emailAuth.setAuthorizationCode(json.getString("authorizationCode"));

        if (userDao.changeUserEmail(userId, email) < 1 && userDao.changeEmailAuth(emailAuth) < 1)
            throw new ServiceException("修改邮箱失败");

        return MyResponseUtil.getResultMap(emailAuth, 0, "success");
    }

    /**
     * 用户申请账号
     * @param jsonObject
     * @return
     */
    @Override
    public Map<String, Object> userApplyForAccount(JSONObject jsonObject) throws ServiceException {
        UserAccountApplying userAccountApplying = new UserAccountApplying();
        String name = jsonObject.getString("name");

        // 先看看user表里有没有这位用户
        if (userDao.getUserByName(name).size() != 0)
            throw new ServiceException("该用户账号已经已经存在，不可重复申请");

        String time = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
        userAccountApplying.setUserAccountId(UUID.randomUUID().toString());
        userAccountApplying.setName(name);
        userAccountApplying.setEmail(jsonObject.getString("email"));
        userAccountApplying.setDescribe(Integer.parseInt(jsonObject.getString("describe")));
        userAccountApplying.setSubmitTime(time);
        userAccountApplying.setAdministratorAudit(false);

        // 如果重复申请，则修改申请表内的信息，否则增加
        List<UserAccountApplying> userAccountApplyings = userDao.getUserAccountApplyingByName(userAccountApplying.getName());
        if (userAccountApplyings.size() != 0) {
            userDao.updateUserAccountApplying(userAccountApplying);
        } else {
            userDao.addUserAccountApplying(userAccountApplying);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("name", userAccountApplying.getName());
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 修改用户信息
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> changeUserDetail(JSONObject map) throws ServiceException {
        String userId = map.getString("userAccountId");
        String name = map.getString("name");
        String password = map.getString("password");
        String email = map.getString("email");

        /**
         * 首先看新修改的名称是不是已有的
         */
        // 首先确保账号存在
        List<User> users = userDao.getUserByUserId(userId);
        if (users.size() == 0) throw new ServiceException("该账号不存在");
        // 然后新名字是否被占用
        String oldName = users.get(0).getName();
        // 如果新旧名字不同，并且新名字已经在使用
        if (!name.equals(oldName) && userDao.getUserByName(name).size() != 0)
            throw new ServiceException("该公司名已经存在");

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        if (userDao.changeUser(user) < 1) throw new ServiceException("修改用户信息失败");

        Map<String, Object> data = new HashMap<>();
        data.put("userAccountId", userId);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }
}