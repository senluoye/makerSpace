package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.UserDao;
import com.qks.makerSpace.service.UserService;
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
//        if ()
        Map<String, Object> data = new HashMap<>();
        return MyResponseUtil.getResultMap(data, 0, null);
    }
}