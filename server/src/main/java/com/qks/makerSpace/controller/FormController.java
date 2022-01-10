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
                                @RequestParam("map") String map,
                                @RequestParam("mediumFile") Object mediumFile,
                                @RequestParam("highEnterpriseFile") Object highEnterpriseFile,
                                @RequestParam("headerFile") Object headerFile,
                                @RequestParam("contractFile") Object[] contractFile,
                                @RequestParam("awardsFile") Object[] awardsFile
                                ) throws ServiceException, IOException {
        MultipartFile[] contractFileList = new MultipartFile[contractFile.length];
        MultipartFile[] awardsFileList = new MultipartFile[awardsFile.length];
        for (int i = 0; i < contractFile.length; i++) {
            contractFileList[i] = (MultipartFile) contractFile[i];
            awardsFileList[i] = (MultipartFile) awardsFile[i];
        }

        return formService.setTechnologyForm(
                httpServletRequest.getHeader("token"),
                JSONObject.parseObject(map),
                (MultipartFile)mediumFile,
                (MultipartFile)highEnterpriseFile,
                (MultipartFile)headerFile,
                contractFileList,
                awardsFileList
        );
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
