package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.database.EmailAuth;
import com.qks.makerSpace.entity.database.User;
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

    @Override
    public Map<String, Object> getHomepage(HttpServletRequest httpServletRequest) {
        Map<String, Object> data = new HashMap<>();
        return MyResponseUtil.getResultMap(data, 0, null);
    }

    /**
     * 管理员修改身份证
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
}