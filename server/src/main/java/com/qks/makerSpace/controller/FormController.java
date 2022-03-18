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
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.POST)
    private Map<String, Object> setTechnologyForm(
                                HttpServletRequest httpServletRequest,
                                @RequestPart("map") String map,
                                @RequestPart(value = "mediumFile", required = false) MultipartFile mediumFile,
                                @RequestPart(value = "highEnterpriseFile", required = false) MultipartFile highEnterpriseFile,
                                @RequestPart(value = "headerFile", required = false) MultipartFile headerFile,
                                @RequestPart("contractFile") MultipartFile[] contractFile,
                                @RequestPart("awardsFile") MultipartFile[] awardsFile
                                ) throws ServiceException, IOException {
        System.out.println("-------------------1--1-------------------");
        System.out.println(map);
        System.out.println(highEnterpriseFile);
        System.out.println(mediumFile);
        System.out.println(highEnterpriseFile);
        System.out.println(headerFile);
        System.out.println(contractFile.length);
        System.out.println(awardsFile.length);
        System.out.println("----------------------------------------");
        return formService.setTechnologyForm(
                httpServletRequest.getHeader("token"),
                JSONObject.parseObject(map),
                mediumFile,
                highEnterpriseFile,
                headerFile,
                contractFile,
                awardsFile
        );
    }

    /**
     * 获取上一次科技园季度报表
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.GET)
    private Map<String, Object> getTechnologyForm(HttpServletRequest httpServletRequest)
            throws ServiceException, IOException {

        return formService.getTechnologyForm(httpServletRequest.getHeader("token"));
    }

    /**
     * @description 获取所有企业的最新季度报表(管理员)
     * @return Hashmap
     */
    @RequestMapping(value = "admin/technology", method = RequestMethod.GET)
    private Map<String, Object> adminGetTechnologyForm(HttpServletRequest httpServletRequest) throws ServiceException {
        return formService.adminGetTechnologyForm(httpServletRequest.getHeader("token"));
    }

    /**
     * @description 获取某一个企业的最新季度报表(用户)
     * @return Hashmap
     */
    @RequestMapping(value = "user/technology", method = RequestMethod.GET)
    private Map<String, Object> userGetTechnologyForm(HttpServletRequest httpServletRequest) throws ServiceException {
        return formService.userGetTechnologyForm(httpServletRequest.getHeader("token"));
    }

    /**
     * 导出科技园在孵企业统计表
     * @param
     * @return
     */
    @RequestMapping(value = "statistical/{creditCode}", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response, @PathVariable String creditCode) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(creditCode));
    }

    /**
     * 导出科技园在孵企业情况表
     * @param
     * @return
     */
    @RequestMapping(value = "situation/{creditCode}", method = RequestMethod.GET)
    private void getSituationForm(HttpServletResponse response,@PathVariable String creditCode) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(creditCode));
    }

    @RequestMapping(value = "space/{inApplyId}", method = RequestMethod.GET)
    private void getSpaceForm(HttpServletResponse response, @PathVariable String inApplyId) throws ServiceException, IllegalAccessException {
        formService.spaceDownLoad(response, inApplyId);
    }

}
