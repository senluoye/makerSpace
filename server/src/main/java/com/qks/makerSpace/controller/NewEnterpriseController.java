package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * @param map
     * @return
     */
    @RequestMapping(value = "newRegister", method = RequestMethod.POST)
    private Map<String, Object> newRegister(@RequestPart("picture") MultipartFile[] file,
                                            @RequestPart("map") Map<String, Object> map) throws Exception {
        if (file.length == 0) {
            return MyResponseUtil.getResultMap(null,-1,"文件上传失败");
        } else {
            return newEnterpriseService.newRegister(map, file);
        }
    }

    /**
     * 缴费
     * @param map
     * @return
     */
    @RequestMapping(value = "newFee", method = RequestMethod.POST)
    private Map<String, Object> NewEnterprisePay(@RequestBody Map<String, Object> map) {
        return newEnterpriseService.newEnterprisePay(map);
    }

    /**
     * 房间申请
     * @param map
     * @return
     */
    @RequestMapping(value = "demand", method = RequestMethod.POST)
    private Map<String, Object> newEnterpriseDemand(@RequestBody JSONObject map) throws ServiceException {
        return newEnterpriseService.newEnterpriseDemand(map);
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @RequestMapping(value = "newEnterprise", method = RequestMethod.PUT)
    private Map<String, Object> updateNewEnterprise(HttpServletRequest httpServletRequest,
                                                    @RequestPart("map") JSONObject map,
                                                    @RequestParam("file") MultipartFile[] file) throws Exception {
        return newEnterpriseService.updateNewEnterprise(httpServletRequest.getHeader("token"), map, file);
    }
}