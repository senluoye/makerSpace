package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.RegisterDao;
import com.qks.makerSpace.entity.database.User;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.RegisterService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final RegisterDao registerDao;

    public RegisterServiceImpl(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    /**
     * 用户注册
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> addNewUser(JSONObject map) throws ServiceException {
        String name = map.getString("name");
        String password = map.getString("password");
        String userId = UUID.randomUUID().toString();
        String email = map.getString("email");

        if (name.equals("admin"))
            throw new ServiceException("用户名违法");

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        if (registerDao.getUserByName(map.getString("name")) != null)
            throw new ServiceException("用户已存在");

        if (registerDao.addNewUser(user) < 1 && registerDao.updateUserCompany(userId) < 1)
            throw new ServiceException("注册失败");

        return MyResponseUtil.getResultMap(userId, 0, "success");

    }
}

