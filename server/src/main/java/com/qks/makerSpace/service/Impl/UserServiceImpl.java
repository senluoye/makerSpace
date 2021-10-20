package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.User;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, Serializable {

    private final UserDao userDao;
    private final JWTUtils jwtUtils;

    public UserServiceImpl(UserDao userDao, JWTUtils jwtUtils) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Map<String, Object> getAllUser() {
        List<User> userList= userDao.getAllUser("admin");

        if (userList != null)
            return MyResponseUtil.getResultMap(userList, 0, "success");

        return MyResponseUtil.getResultMap(null, -1, "返回用户数据失败");
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
            String token = jwtUtils.createToken(userMap);
            data.put("token", token);
            return MyResponseUtil.getResultMap(data, 0, "success");
        } else
            return MyResponseUtil.getResultMap(null, -1, "用户不存在或密码错粗");
    }

    @Override
    public Map<String, Object> register(Map<String, Object> map) {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        String id = UUID.randomUUID().toString();

        if (name.equals("admin") && password.equals("123456"))
            return MyResponseUtil.getResultMap(null, -1, "该用户名为管理员所有");

        if (userDao.getUserByNameAndPassword(name, password) == null)
            if (userDao.addUser(id, name, password) > 0)
                return MyResponseUtil.getResultMap(id, 0, "success");

        return MyResponseUtil.getResultMap(null, -1, "用户已经存在");
    }
}

