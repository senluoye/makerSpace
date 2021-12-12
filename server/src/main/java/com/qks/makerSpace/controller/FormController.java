package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.FormService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/form")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }


    /**
     * 导出科技园在孵企业统计表
     * @param
     * @return
     */
    @RequestMapping(value = "statistical", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(),1);
    }

    /**
     * 导出科技园在孵企业情况表
     * @param
     * @return
     */
    @RequestMapping(value = "situation", method = RequestMethod.GET)
    private void getSituationForm(HttpServletResponse response) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(),2);
    }

}
