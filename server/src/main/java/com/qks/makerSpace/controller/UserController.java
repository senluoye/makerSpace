package com.qks.makerSpace.controller;

import com.qks.makerSpace.entity.User;
import com.qks.makerSpace.service.UserService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/login")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登陆
     * @return map
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    private Map<String, Object> Login(@RequestBody Map<String, Object> map) {
        return userService.login(map);
    }
}
