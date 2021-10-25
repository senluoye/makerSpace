package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.LoginDao;
import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.User;
import com.qks.makerSpace.service.LoginService;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService, Serializable {

    private final LoginDao loginDao;
    private final JWTUtils jwtUtils;

    public LoginServiceImpl(LoginDao loginDao, JWTUtils jwtUtils) {
        this.loginDao = loginDao;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public Map<String, Object> leaderLogin(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        User user = new User();

        String name = map.get("name").toString();
        String password = map.get("password").toString();

        user.setName(name);
        user.setPassword(password);

        loginDao.leaderLogin();

        return result;
    }

    @Override
    public Map<String, Object> adminLogin(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> oldLogin(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> newLogin(Map<String, Object> map) {
        return null;
    }
}

