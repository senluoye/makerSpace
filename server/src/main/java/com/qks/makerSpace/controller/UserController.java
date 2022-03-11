package com.qks.makerSpace.controller;

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
        return userService.getHomepage(httpServletRequest);
    }
}
