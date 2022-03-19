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

import javax.servlet.http.HttpServletRequest;
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
            return MyResponseUtil.getResultMap(homePageRes, 0, "success");
        }

        // 之前已经提交过入园申请
        String creditCode = userCompany.get(0).getCreditCode();
        if (user.getUserDescribe() == 3) { // 如果用户是new
            News news = userDao.getLastNewByCreditCode(creditCode);
            homePageRes.setSubmitTime(news.getSubmitTime());
            homePageRes.setName(news.getName());
            NewDemand newDemand = userDao.getLastNewDemandById(news.getNewDemandId());
            homePageRes.setDemandTime(newDemand.getTime());
        } else if (user.getUserDescribe() == 4) { // 如果用户是old
            Old old = userDao.getLastOldByCreditCode(creditCode);
            homePageRes.setSubmitTime(old.getSubmitTime());
            homePageRes.setName(old.getName());
            OldDemand oldDemand = userDao.getLastOldDemandById(old.getOldDemandId());
            homePageRes.setDemandTime(oldDemand.getTime());
        } else if (user.getUserDescribe() == 5) { // 如果用户是space
            Space space = userDao.getLastSpaceById(creditCode);
            homePageRes.setName(space.getCreateName());
            homePageRes.setSubmitTime(space.getSubmitTime());
            homePageRes.setDemandTime("");
        } else {
            homePageRes.setName("admin");
            homePageRes.setSubmitTime(null);
            homePageRes.setDemandTime(null);
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

//    /**
//     * 用户申请账号
//     * @param token
//     * @param jsonObject
//     * @return
//     * @throws ServiceException
//     */
//    @Override
//    public Map<String, Object> userApplyForAccount(JSONObject jsonObject) throws ServiceException {
//        UserAccountApplying userAccountApplying = JSONObject.parseObject(String.valueOf(jsonObject), UserAccountApplying.class);
////        userDao.addUserAccountApplying(userAccountApplying);
//
//        return null;
//    }
}