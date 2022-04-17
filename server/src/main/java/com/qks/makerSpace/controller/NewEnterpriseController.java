package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.PageRanges;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/api/new")
public class NewEnterpriseController {

    private final NewEnterpriseService newEnterpriseService;

    public NewEnterpriseController(NewEnterpriseService newEnterpriseService) {
        this.newEnterpriseService = newEnterpriseService;
    }


    /**
     * 入园申请（含租赁）
     * @param httpServletRequest
     * @param str
     * @param picture
     * @param representCard
     * @param certificate
     * @param intellectualFile
     * @return
     */
    @RequestMapping(value = "newEnterprise", method = RequestMethod.POST)
    private Map<String, Object> updateNewEnterprise(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") String str,
                                                    @RequestPart("picture") MultipartFile picture,
                                                    @RequestPart("representCard") MultipartFile representCard,
                                                    @RequestPart(value = "certificate", required = false) MultipartFile certificate,
                                                    @RequestPart(value = "intellectualFile", required = false) MultipartFile[] intellectualFile) throws Exception {
        return newEnterpriseService.updateNewEnterprise(
                httpServletRequest.getHeader("token"),
                str,
                picture,
                representCard,
                certificate,
                intellectualFile);
    }

    /**
     * 获取上一次入园申请
     * @param
     * @return
     */
    @RequestMapping(value = "newEnterprise/{id}", method = RequestMethod.GET)
    private Map<String, Object> getNewEnterprise(@PathVariable String id) {
        return newEnterpriseService.getNewEnterprise(id);
    }

    /**
     * 获取以往入园申请记录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "newEnterprise/applying",method = RequestMethod.GET)
    private Map<String, Object> getNewEnterpriseApplying(HttpServletRequest httpServletRequest) {
        return newEnterpriseService.getNewEnterpriseApplying(httpServletRequest.getHeader("token"));
    }

    /**
     * 续约管理
     * @param httpServletRequest
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "demand", method = RequestMethod.POST)
    private Map<String, Object> newEnterpriseDemand(HttpServletRequest httpServletRequest,
                                                    @RequestBody JSONObject jsonObject) throws ServiceException {
        return newEnterpriseService.newEnterpriseContract(httpServletRequest.getHeader("token"), jsonObject);
    }

    /**
     * 缴费管理
     * @param httpServletRequest
     * @param str
     * @param voucher
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "amount", method = RequestMethod.POST)
    private Map<String, Object> newEnterpriseAmount(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") String str,
                                                    @RequestPart("paymentVoucher") MultipartFile voucher) throws ServiceException {
        if (voucher == null) {
            throw new ServiceException("缺少缴费凭证");
        }
        JSONObject json = JSONObject.parseObject(str);
        return newEnterpriseService.newEnterpriseAmount(httpServletRequest.getHeader("token"), json, voucher);
    }

    /**
     * 获取以往续约记录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "demand", method = RequestMethod.GET)
    private Map<String, Object> getNewEnterpriseDemand(HttpServletRequest httpServletRequest) throws ServiceException {
        return newEnterpriseService.getNewEnterpriseDemand(httpServletRequest.getHeader("token"));
    }

    /**
     * 获取以往缴费记录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "amount", method = RequestMethod.GET)
    private Map<String, Object> getNewEnterpriseContract(HttpServletRequest httpServletRequest) throws ServiceException {
        return newEnterpriseService.getNewEnterpriseContract(httpServletRequest.getHeader("token"));
    }

    /**
     * 获取某个企业的所有季度报表
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/form/technology", method = RequestMethod.GET)
    private Map<String, Object> getFormByCreditCode(HttpServletRequest httpServletRequest) throws Exception {
        return newEnterpriseService.getFormByCreditCode(httpServletRequest.getHeader("token"));
    }
}