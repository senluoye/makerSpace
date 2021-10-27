package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.LoginService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 领导登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "leader", method = RequestMethod.POST)
    private Map<String, Object> leaderLogin(@RequestBody Map<String, Object> map) {
        return loginService.leaderLogin(map);
    }

    /**
     * 管理员登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "admin", method = RequestMethod.POST)
    private Map<String, Object> adminLogin(@RequestBody Map<String, Object> map) {
        return loginService.adminLogin(map);
    }

    /**
     * 迁入或独立注册企业登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "old", method = RequestMethod.POST)
    private Map<String, Object> oldLogin(@RequestBody Map<String, Object> map) {
        return loginService.oldLogin(map);
    }

    /**
     * 新成立或非独立注册企业登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    private Map<String, Object> newLogin(@RequestBody Map<String, Object> map) {
        return loginService.newLogin(map);
    }
}