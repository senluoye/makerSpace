package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/form")
public class FormController {

//    @Autowired
//    private FormService formService;

    /**
     * 导出科技园在孵企业统计表
     * @param map
     * @return
     */
    @RequestMapping(value = "statistical", method = RequestMethod.GET)
    private void getStatisticalForm() {
//        return oldRegisterService.getOldForm();
    }

    /**
     * 导出科技园在孵企业情况表
     * @param map
     * @return
     */
    @RequestMapping(value = "situation", method = RequestMethod.GET)
    private void getSituationForm() {
//        return oldRegisterService.getOldForm();
    }

}
