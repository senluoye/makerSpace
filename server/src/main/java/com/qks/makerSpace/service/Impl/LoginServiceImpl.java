package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.LoginDao;
import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.entity.User;
import com.qks.makerSpace.service.LoginService;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.apache.logging.log4j.message.ReusableMessage;
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
    public Map<String, Object> getAllUser() {
        List<User> userList= loginDao.getAllUser("admin");

        if (userList != null)
            return MyResponseUtil.getResultMap(userList, 0, "success");

        return MyResponseUtil.getResultMap(null, -1, "返回用户数据失败");
    }

    @Override
    public Map<String, Object> AdminOrLeaderLogin(Map<String, Object> map) {

        String name = map.get("name").toString();
        String password = map.get("password").toString();

        Map<String, Object> data = new HashMap<>();

        //领导唯一账号 leader
        //
        if (loginDao.AdminOrLeaderLogin(name,password) != null) {

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name",name);
            userMap.put("password",password);

            String token = jwtUtils.createToken(userMap);
            data.put("token",token);
            if (name == "leader") {
                data.put("role","leader");
            } else {
                data.put("role","admin");
            }
            return MyResponseUtil.getResultMap(data,0,"success");
        } else {
            return MyResponseUtil.getResultMap(null,-1,"用户不存在或密码错误");
        }

    }


    @Override
    public Map<String, Object> oldLogin(Map<String, Object> map) {

        String username = map.get("username").toString();
        String password = map.get("password").toString();

        Map<String, Object> data = new HashMap<>();

        if (loginDao.oldLogin(username,password) != null) {

            Map<String, Object> oldMap = new HashMap<>();
            oldMap.put("username",username);
            oldMap.put("password",password);

            String token = jwtUtils.createToken(oldMap);
            data.put("token",token);
            return MyResponseUtil.getResultMap(data,0,"success");
        } else {
            return MyResponseUtil.getResultMap(null,-1,"用户不存在或密码错误");
        }
    }

    @Override
    public Map<String, Object> newLogin(Map<String, Object> map) {

        String username = map.get("username").toString();
        String password = map.get("password").toString();

        Map<String, Object> data = new HashMap<>();

        if (loginDao.newLogin(username,password) != null) {

            Map<String, Object> newMap = new HashMap<>();
            newMap.put("username",username);
            newMap.put("password",password);

            String token = jwtUtils.createToken(newMap);
            data.put("token",token);
            return MyResponseUtil.getResultMap(data,0,"success");
        } else {
            return MyResponseUtil.getResultMap(null,-1,"用户不存在或密码错误");
        }
    }

}

