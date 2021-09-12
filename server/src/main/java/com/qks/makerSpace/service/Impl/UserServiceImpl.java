package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final MyResponseUtil myResponseUtil;
    private final JWTUtils jwtUtils;

    public UserServiceImpl(MyResponseUtil myResponseUtil, UserDao userDao, JWTUtils jwtUtils) {
        this.myResponseUtil = myResponseUtil;
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Map<String, Object> login(Map<String, Object> map) {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        if (userDao.getUserByNameAndPassword(name, password) != null){
            Map<String, Object> data = new HashMap<>();
            Map<String, Object> userMap = new HashMap<>();

            userMap.put("name", name);
            userMap.put("password", password);
            data.put("token", jwtUtils.createToken(userMap));
            return myResponseUtil.getResultMap(data, 0, "success");
        } else
            return myResponseUtil.getResultMap(null, -1, "The user name or password is incorrect");
    }
}

