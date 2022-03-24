package com.qks.makerSpace.controller;

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
     * 续约
     * @param json
     * @param voucher
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    @RequestMapping(value = "demand", method = RequestMethod.PUT)
    private Map<String, Object> oldEnterprisePay(@RequestPart("map") String json,
                                                 @RequestPart("paymentVoucher") MultipartFile voucher) throws ServiceException, IOException {
        if (voucher == null)
            throw new ServiceException("缺少缴费凭证");

        return newEnterpriseService.newEnterpriseContract(json, voucher);
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