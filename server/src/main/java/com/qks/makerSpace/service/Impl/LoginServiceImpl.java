package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.LoginDao;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.LoginService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService, Serializable {

    private final LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    /**
     * 领导登陆
     * @param Hashmap
     * @return Hashmap
     */
    @Override
    public Map<String, Object> LeaderLogin(Map<String, Object> map) throws ServiceException {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        String userId = loginDao.adminOrLeaderLogin(name,password,0);

        Map<String, Object> data = new HashMap<>();

        if (userId != null) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name",name);
            userMap.put("userId", userId);
            data.put("token", JWTUtils.createToken(userMap));
        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 管理员登陆
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> AdminLogin(Map<String, Object> map) throws ServiceException {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        String userId = loginDao.adminOrLeaderLogin(name, password, 1);

        Map<String, Object> data = new HashMap<>();

        if (userId != null) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name", name);
            userMap.put("userId", userId);
            data.put("token", JWTUtils.createToken(userMap));
        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 普通用户登陆
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> CommonLogin(JSONObject map) throws ServiceException {
        String username = map.get("name").toString();
        String password = map.get("password").toString();
        String userId = loginDao.commonLogin(username, password);

        Map<String, Object> data = new HashMap<>();

        if (userId != null) {
            Map<String, Object> user = new HashMap<>();
            user.put("name",username);
            user.put("userId", userId);
            data.put("token", JWTUtils.createToken(user));
        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data,0,"success");
    }

}

