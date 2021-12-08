package com.qks.makerSpace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    private void getStatisticalForm() {
//        return oldRegisterService.getOldForm();
    }

}
