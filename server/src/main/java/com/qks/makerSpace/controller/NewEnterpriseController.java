package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * @param map
     * @return
     */
    @RequestMapping(value = "newRegister", method = RequestMethod.POST)
    private Map<String, Object> newRegister(@RequestPart("picture") MultipartFile[] file,@RequestParam Map<String,Object> map) throws IOException {
        if (file.length == 0) {
            return MyResponseUtil.getResultMap(null,-1,"文件上传失败");
        } else {
            return newEnterpriseService.newRegister(map,file);
        }
    }

    /**
     * 租赁缴费
     * @param map
     * @return
     */
    @RequestMapping(value = "newFee", method = RequestMethod.POST)
    private Map<String, Object> NewEnterprisePay(@RequestBody Map<String, Object> map) {
        return newEnterpriseService.newEnterprisePay(map);
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @RequestMapping(value = "newEnterprise", method = RequestMethod.PUT)
    private Map<String, Object> updateNewEnterprise(@RequestHeader String token,
                                                    @RequestParam("map") Map<String, Object> map,
                                                    @RequestPart("file") MultipartFile[] file) throws IllegalAccessException,IOException {
        return newEnterpriseService.updateNewEnterprise(token, map, file);
    }
}