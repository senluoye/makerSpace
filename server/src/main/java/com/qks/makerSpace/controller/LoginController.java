package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
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
     * @param Hashmap
     * @return Hashmap
     */
    @RequestMapping(value = "leader", method = RequestMethod.POST)
    private Map<String, Object> LeaderLogin(@RequestBody Map<String, Object> map) throws ServiceException {
        return loginService.LeaderLogin(map);
    }

    /**
     * 科技园管理员登陆
     * @param map
     * @return
     */
    @RequestMapping(value = "admin", method = RequestMethod.POST)
    private Map<String, Object> TecAdminLogin(@RequestBody Map<String, Object> map) throws ServiceException {
        return loginService.TecAdminLogin(map);
    }

    /**
     * 众创空间管理员登陆
     * @param map
     * @return
     */
//    @RequestMapping(value = "admin", method = RequestMethod.POST)
//    private Map<String, Object> SpaceAdminLogin(@RequestBody Map<String, Object> map) throws ServiceException {
//        return loginService.SpaceAdminLogin(map);
//    }

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
