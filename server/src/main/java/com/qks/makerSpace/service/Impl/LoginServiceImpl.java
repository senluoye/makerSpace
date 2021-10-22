package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.LoginDao;
import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.User;
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
public class LoginServiceImpl implements UserService, Serializable {

    private final LoginDao loginDao;
    private final JWTUtils jwtUtils;

    public LoginServiceImpl(LoginDao loginDao, JWTUtils jwtUtils) {
        this.loginDao = loginDao;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Map<String, Object> getAllUser() {
        List<User> userList= userDao.getAllUser("admin");

        if (userList != null)
            return MyResponseUtil.getResultMap(userList, 0, "success");

        return MyResponseUtil.getResultMap(null, -1, "返回用户数据失败");
    }

}

