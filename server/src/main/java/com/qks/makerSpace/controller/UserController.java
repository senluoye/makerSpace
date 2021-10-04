package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private Map<String, Object> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * 登陆
     * @return map
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    private Map<String, Object> Login(@RequestBody Map<String, Object> map) {
        return userService.login(map);
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    private Map<String, Object> Register(@RequestBody Map<String, Object> map) {
        return userService.register(map);
    }

}
