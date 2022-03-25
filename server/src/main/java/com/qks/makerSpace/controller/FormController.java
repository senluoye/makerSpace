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
     * 获取某一次科技园季度报表
     * @return
     */
    @RequestMapping(value = "technology/{formId}", method = RequestMethod.GET)
    private Map<String, Object> getTechnologyFormById(HttpServletRequest httpServletRequest,
                                                  @PathVariable("formId") String formId)
            throws ServiceException, IOException {
        return formService.getTechnologyFormById(httpServletRequest.getHeader("token"), formId);
    }

    /**
     * 获取季度报表中的固定部分
     * @return
     */
    @RequestMapping(value = "technology/basic", method = RequestMethod.GET)
    private Map<String, Object> getTechnologyBasic(HttpServletRequest httpServletRequest)
            throws ServiceException, IOException {
        return formService.getTechnologyBasic(httpServletRequest.getHeader("token"));
    }

    /**
     * @description 获取所有企业最新的季度报表(管理员)
     * @return Hashmap
     */
    @RequestMapping(value = "admin/technology", method = RequestMethod.GET)
    private Map<String, Object> adminGetTechnologyForm(HttpServletRequest httpServletRequest) throws ServiceException {
        return formService.adminGetTechnologyForm(httpServletRequest.getHeader("token"));
    }

    /**
     * @description 获取某个企业的所有季度报表部分信息（用户）
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
    @RequestMapping(value = "situation/{id}", method = RequestMethod.GET)
    private void getSituationForm(HttpServletResponse response,@PathVariable String formId) throws Exception {
        formService.downLoadWord(response, formService.getDownLoadForm(formId));
    }

    @RequestMapping(value = "space/{inApplyId}", method = RequestMethod.GET)
    private void getSpaceForm(HttpServletResponse response, @PathVariable String inApplyId) throws ServiceException, IllegalAccessException {
        formService.spaceDownLoad(response, inApplyId);
    }

}
