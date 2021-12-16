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
    private Map<String, Object> oldRegister(@RequestParam Map<String, Object> map) {
        return oldEnterpriseService.oldRegister(map);
    }

    /**
     * 缴费
     * @param map
     * @return
     */
    @RequestMapping(value = "fee", method = RequestMethod.POST)
    private Map<String, Object> oldEnterprisePay(@RequestBody Map<String, Object> map) {
        return oldEnterpriseService.oldEnterprisePay(map);
    }

    /**
     * 科技园场地申请
     * @param map
     * @return
     */
    @RequestMapping(value = "/old/demand", method = RequestMethod.POST)
    private Map<String, Object> oldEnterpriseDemand(@RequestBody JSONObject map) throws ServiceException {
        return oldEnterpriseService.oldEnterpriseDemand(map);
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @RequestMapping(value = "oldEnterprise", method = RequestMethod.PUT)
    private Map<String, Object> updateOldEnterprise(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") JSONObject map,
                                                    @RequestParam("file") MultipartFile[] file) throws Exception {

        return oldEnterpriseService.updateOldEnterprise(httpServletRequest.getHeader("token"), map, file);
    }

}
