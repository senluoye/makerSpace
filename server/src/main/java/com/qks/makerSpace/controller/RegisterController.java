package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.RegisterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * 普通成员注册
     * @return Hashmap
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    private Map<String, Object> newLogin(@RequestBody JSONObject map) throws ServiceException {
        return registerService.addNewUser(map);
    }

}
