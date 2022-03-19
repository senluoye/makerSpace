package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户获取主页信息
     * @param Hashmap
     * @return Hashmap
     */
    @RequestMapping(value = "homepage", method = RequestMethod.GET)
    private Map<String, Object> getHomepage(HttpServletRequest httpServletRequest) throws ServiceException {
        return userService.getHomepage(httpServletRequest.getHeader("token"));
    }

    /**
     * 管理员修改密码
     * @param Hashmap
     * @return Hashmap
     */
    @RequestMapping(value = "admin/email", method = RequestMethod.PUT)
    private Map<String, Object> changeAdminEmail(HttpServletRequest httpServletRequest,
                                                 @RequestBody JSONObject jsonObject) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        return userService.changeAdminEmail(token, jsonObject);
    }

    /**
     * 用户申请账号
     * @param Hashmap
     * @return Hashmap
     */
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    private Map<String, Object> userApplyForAccount(@RequestBody JSONObject jsonObject) throws ServiceException {
        return userService.userApplyForAccount(jsonObject);
    }
}
