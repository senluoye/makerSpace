package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 领导和管理员登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "header", method = RequestMethod.POST)
    private Map<String, Object> AdminOrLeaderLogin(@RequestBody Map<String, Object> map) {
        return loginService.AdminOrLeaderLogin(map);
    }

    /**
     * 普通成员登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "common", method = RequestMethod.POST)
    private Map<String, Object> CommonLogin(@RequestBody JSONObject map) throws ServiceException {
        return loginService.CommonLogin(map);
    }

}
