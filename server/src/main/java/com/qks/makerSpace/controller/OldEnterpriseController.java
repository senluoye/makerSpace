package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.OldEnterpriseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OldEnterpriseController {

    private final OldEnterpriseService oldEnterpriseService;

    public OldEnterpriseController(OldEnterpriseService oldEnterpriseService) {
        this.oldEnterpriseService = oldEnterpriseService;
    }

    /**
     * 入园申请表填写(含更新)
     * @param str
     * @return
     */
    @RequestMapping(value = "old/oldEnterprise", method = RequestMethod.POST)
    private Map<String, Object> updateOldEnterprise(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") String str,
                                                    @RequestPart(value = "license", required = false) MultipartFile license,
                                                    @RequestPart(value = "certificate", required = false) MultipartFile certificate,
                                                    @RequestPart(value = "intellectualFile", required = false) MultipartFile[] intellectualFile,
                                                    @RequestPart(value = "representFile", required = false) MultipartFile representFile) throws Exception {
        return oldEnterpriseService.updateOldEnterprise(
                httpServletRequest.getHeader("token"),
                str,
                license,
                certificate,
                intellectualFile,
                representFile);
    }

    /**
     * 获取上一次入园申请
     * @param
     * @return
     */
    @RequestMapping(value = "old/oldEnterprise", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterprise(HttpServletRequest httpServletRequest) {
        return oldEnterpriseService.getOldEnterprise(httpServletRequest.getHeader("token"));
    }

    /**
     * 获取某一次入园申请
     * @param
     * @return
     */
    @RequestMapping(value = "old/oldEnterprise/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterpriseById(HttpServletRequest httpServletRequest,
                                                     @PathVariable("id") String id) {
        return oldEnterpriseService.getOldEnterpriseById(httpServletRequest.getHeader("token"), id);
    }

    /**
     * 获取以往所有入园申请记录
     * @param
     * @return
     */
    @RequestMapping(value = "old/oldEnterprise/applying", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterpriseApplying(HttpServletRequest httpServletRequest) {
        return oldEnterpriseService.getOldEnterpriseApplying(httpServletRequest.getHeader("token"));
    }

    /**
     * 续约
     * @param map
     * @return
     */
    @RequestMapping(value = "old/demand", method = RequestMethod.POST)
    private Map<String, Object> oldEnterprisePay(HttpServletRequest httpServletRequest,
                                                 @RequestPart(value = "paymentVoucher", required = false) MultipartFile voucher) throws ServiceException, IOException {
        if (voucher == null)
            throw new ServiceException("缺少缴费凭证");
        return oldEnterpriseService.oldEnterpriseContract(httpServletRequest.getHeader("token"), voucher);
    }

    /**
     * 获取以往缴费记录
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    @RequestMapping(value = "old/demand", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterprisePay(HttpServletRequest httpServletRequest) throws ServiceException, IOException {
        return oldEnterpriseService.getOldEnterpriseContract(httpServletRequest.getHeader("token"));
    }

    /**
     * 获取某个企业的所有季度报表
     * @param str
     * @return
     */
    @RequestMapping(value = "old/form/technology", method = RequestMethod.GET)
    private Map<String, Object> getFormByCreditCode(HttpServletRequest httpServletRequest) throws Exception {
        return oldEnterpriseService.getFormByCreditCode(httpServletRequest.getHeader("token"));
    }
}
