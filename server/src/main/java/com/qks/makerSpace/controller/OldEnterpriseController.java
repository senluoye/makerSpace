package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.OldEnterpriseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/old")
public class OldEnterpriseController {

    private final OldEnterpriseService oldEnterpriseService;

    public OldEnterpriseController(OldEnterpriseService oldEnterpriseService) {
        this.oldEnterpriseService = oldEnterpriseService;
    }

    /**
     * 导出信息表
     * @param map
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    private void getOldForm() {
//        return oldRegisterService.getOldForm();
    }

    /**
     * 信息状态展示
     * @param map
     * @return
     */
    @RequestMapping(value = "oldEnterprise", method = RequestMethod.GET)
    private Map<String, Object> getOldEnterprise() {
        return oldEnterpriseService.getOldEnterprise();
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "oldRegister", method = RequestMethod.POST)
    private Map<String, Object> oldRegister(@RequestBody Map<String, Object> map) {
        return oldEnterpriseService.oldRegister(map);
    }

    /**
     * 众创空间场地申请
     * @param map
     * @return
     */
    @RequestMapping(value = "space", method = RequestMethod.POST)
    private Map<String, Object> applyForMakerSpace(@RequestBody Map<String, Object> map) {
        return oldEnterpriseService.applyForMakerSpace(map);
    }

    /**
     * 租赁缴费
     * @param map
     * @return
     */
    @RequestMapping(value = "fee", method = RequestMethod.POST)
    private Map<String, Object> oldEnterprisePay(@RequestBody Map<String, Object> map) {
        return oldEnterpriseService.oldEnterprisePay(map);
    }

    /**
     * 入园申请表填写
     * @param map
     * @return
     */
    @RequestMapping(value = "oldEnterprise", method = RequestMethod.PUT)
    private Map<String, Object> updateOldEnterprise(@RequestHeader String token, @RequestBody Map<String, Object> map) throws IllegalAccessException {
        return oldEnterpriseService.updateOldEnterprise(token, map);
    }

    /**
     * 众创空间退出
     * @param map
     * @return
     */
    @RequestMapping(value = "space", method = RequestMethod.DELETE)
    private Map<String, Object> quitMakerSpace(@RequestBody Map<String, Object> map) {
        return oldEnterpriseService.quitMakerSpace(map);
    }
}
