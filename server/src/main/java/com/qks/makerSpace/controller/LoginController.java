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
    private Map<String, Object> Leader(@RequestBody Map<String, Object> map) {
        return loginService.leader(map);
    }

    /**
     * 领导登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "admin", method = RequestMethod.POST)
    private Map<String, Object> Admin(@RequestBody Map<String, Object> map) {
        return loginService.admin(map);
    }

    /**
     * 领导登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "old", method = RequestMethod.POST)
    private Map<String, Object> Old(@RequestBody Map<String, Object> map) {
        return loginService.old(map);
    }

    /**
     * 领导登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    private Map<String, Object> New(@RequestBody Map<String, Object> map) {
        return loginService.new(map);
    }
}
