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
     * 信息状态展示
     * @param
     * @return
     */
    @RequestMapping(value = "oldEnterprise", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterprise() {
        return oldEnterpriseService.getOldEnterprise();
    }

    /**
     * 迁入企业注册
     * @param map
     * @return
     */
    @RequestMapping(value = "oldRegister", method = RequestMethod.POST)
    private Map<String, Object> oldRegister(@RequestBody JSONObject map) throws ServiceException {
        return oldEnterpriseService.oldRegister(map);
    }

    /**
     * 续约
     * @param map
     * @return
     */
    @RequestMapping(value = "old/demand", method = RequestMethod.PUT)
    private Map<String, Object> oldEnterprisePay(@RequestPart("map") String json,
                                                 @RequestPart("paymentVoucher") MultipartFile voucher) throws ServiceException, IOException {
        if (voucher == null)
            throw new ServiceException("缺少缴费凭证");

        return oldEnterpriseService.oldEnterpriseContract(json, voucher);
    }

    /**
     * 申请房间
     * @param map
     * @return
     */
    @RequestMapping(value = "old/demand", method = RequestMethod.POST)
    private Map<String, Object> oldEnterpriseDemand(@RequestBody JSONObject map) throws ServiceException {
        return oldEnterpriseService.oldEnterpriseDemand(map);
    }

    /**
     * 入园申请表填写
     * @param str
     * @return
     */
    @RequestMapping(value = "oldEnterprise", method = RequestMethod.PUT)
    private Map<String, Object> updateOldEnterprise(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") String str,
                                                    @RequestPart("file") MultipartFile[] file) throws Exception {
        return oldEnterpriseService.updateOldEnterprise(httpServletRequest.getHeader("token"), str, file);
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
