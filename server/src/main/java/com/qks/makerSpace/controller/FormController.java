package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.FormService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/form")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * 填写科技园季度报表
     * @param
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.POST)
    private Map<String, Object> setTechnologyForm(
                                HttpServletRequest httpServletRequest,
                                @RequestPart("map") String map,
                                @RequestPart("mediumFile") MultipartFile mediumFile,
                                @RequestPart("highEnterpriseFile") MultipartFile highEnterpriseFile,
                                @RequestPart("headerFile") MultipartFile headerFile,
                                @RequestPart("contractFile") MultipartFile[] contractFile,
                                @RequestPart("awardsFile") MultipartFile[] awardsFile
                                ) throws ServiceException, IOException {
        return formService.setTechnologyForm(
                httpServletRequest.getHeader("token"),
                map,
                mediumFile,
                highEnterpriseFile,
                headerFile,
                contractFile,
                awardsFile);
    }

    /**
     * 导出科技园在孵企业统计表
     * @param
     * @return
     */
    @RequestMapping(value = "statistical/{creditCode}", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response, @PathVariable String creditCode) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(creditCode),1);
    }

    /**
     * 导出科技园在孵企业情况表
     * @param
     * @return
     */
    @RequestMapping(value = "situation/{creditCode}", method = RequestMethod.GET)
    private void getSituationForm(HttpServletResponse response,@PathVariable String creditCode) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(creditCode),2);
    }

}
