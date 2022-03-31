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
     * @param Hashmap
     * @return Hashmap
     */
    @Override
    public Map<String, Object> LeaderLogin(Map<String, Object> map) throws ServiceException {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(name, password, 0);

        Integer userDescribe = user.getUserDescribe();
        Map<String, Object> data = new HashMap<>();
        if (userDescribe != null) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name",name);
            userMap.put("userDescribe", userDescribe);
            data.put("token", JWTUtils.createToken(userMap));
        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data,0,"success");
    }

    /**
     * 科技园管理员登陆
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> TecAdminLogin(Map<String, Object> map) throws ServiceException {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(name, password, 11);
        if (user == null) throw new ServiceException("请输入正确的账号和密码");

        Integer userDescribe = user.getUserDescribe();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("userDescribe", userDescribe);
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
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        User user = loginDao.adminOrLeaderLogin(name, password, 12);
        if (user == null) throw new ServiceException("请输入正确的账号和密码");

        Integer userDescribe = user.getUserDescribe();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("name", name);
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
        String username = map.get("name").toString();
        String password = map.get("password").toString();
        String userId = loginDao.commonLogin(username, password);

        Map<String, Object> data = new HashMap<>();
        if (userId != null) {
            int alive = loginDao.selectAlive(username, password);
            if (alive == 1) {
                Map<String, Object> user = new HashMap<>();
                user.put("name",username);
                user.put("userId", userId);
                data.put("token", JWTUtils.createToken(user));
            } else {
                throw new ServiceException("该账户已注销");
            }

        } else throw new ServiceException("用户不存在或密码错误");

        return MyResponseUtil.getResultMap(data,0,"success");
    }

}

