package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.LoginService;
import com.qks.makerSpace.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
     * @param map
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    private Map<String, Object> newLogin(@RequestBody JSONObject map) throws ServiceException {
        return registerService.addNewUser(map);
    }

}
