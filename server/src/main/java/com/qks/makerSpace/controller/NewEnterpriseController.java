package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 信息状态展示
     * @param
     * @return
     */
    @RequestMapping(value = "newEnterprise", method = RequestMethod.GET)
    private Map<String, Object> getNewEnterprise() {
        return newEnterpriseService.getNewEnterprise();
    }

    /**
     * 注册
     * @param httpServletRequest
     * @param map
     * @param picture
     * @param representCard
     * @param certificate
     * @param intellectualFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "newRegister", method = RequestMethod.POST)
    private Map<String, Object> newRegister(HttpServletRequest httpServletRequest,
                                            @RequestParam("map") String map,
                                            @RequestParam(value = "picture") MultipartFile picture,
                                            @RequestParam(value = "representCard") MultipartFile representCard,
                                            @RequestParam(value = "certificate") MultipartFile certificate,
                                            @RequestParam(value = "intellectualFile", required = false) MultipartFile[] intellectualFile) throws Exception {
        return newEnterpriseService.newRegister(
                httpServletRequest.getHeader("token"),
                JSONObject.parseObject(map),
                picture,
                representCard,
                certificate,
                intellectualFile);
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