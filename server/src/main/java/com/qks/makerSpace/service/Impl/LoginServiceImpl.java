package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.LoginDao;
import com.qks.makerSpace.entity.database.User;
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
     * @param map
     * @return Hashmap
     */
    @Override
    public Map<String, Object> LeaderLogin(Map<String, Object> map) throws ServiceException {
        String id = map.get("id").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(id, password, 0);
        if (user == null) throw new ServiceException("请输入正确的账号和密码");
        if (user.getUserDescribe() != 0)
            throw new ServiceException("用户不是领导");

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("userDescribe", user.getUserDescribe());
        data.put("token", JWTUtils.createToken(userMap));

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 科技园管理员登陆
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> TecAdminLogin(Map<String, Object> map) throws ServiceException {
        String id = map.get("id").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(id, password, 11);
        if (user == null) throw new ServiceException("请输入正确的账号和密码");
        if (user.getUserDescribe() != 11 && user.getUserDescribe() != 12)
            throw new ServiceException("用户不是管理员");

        Map<String, Object> data = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("userDescribe", user.getUserDescribe());
        data.put("token", JWTUtils.createToken(userMap));

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 众创空间管理员登陆
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> SpaceAdminLogin(Map<String, Object> map) throws ServiceException {
        String id = map.get("id").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(id, password, 12);
        if (user == null) throw new ServiceException("请输入正确的账号和密码");
        if (user.getUserDescribe() != 11 && user.getUserDescribe() != 12)
            throw new ServiceException("用户不是管理员");

        Integer userDescribe = user.getUserDescribe();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", user.getName());
        userMap.put("userDescribe", userDescribe);
        data.put("token", JWTUtils.createToken(userMap));

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
        String id = map.get("id").toString();
        String password = map.get("password").toString();
        User user = loginDao.commonLogin(id, password);
        if (user.getUserDescribe() == 11 || user.getUserDescribe() == 12 || user.getUserDescribe() == 0)
            throw new ServiceException("不存在该用户账号");

//        System.out.println(user);
        Map<String, Object> data = new HashMap<>();
        if (user != null) {
            if (user.getAlive()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("name", user.getName());
                userMap.put("userId", user.getUserId());
                userMap.put("userDescribe", user.getUserDescribe());
                userMap.put("email", user.getEmail());
                data.put("token", JWTUtils.createToken(userMap));
            } else throw new ServiceException("该账户已注销");
        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data, 0, "success");
    }
}

