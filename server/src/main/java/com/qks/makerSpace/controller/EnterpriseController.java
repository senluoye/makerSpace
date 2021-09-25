package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.EnterpriseService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Map<String, Object> getOneEnterprise(@PathVariable String id) {
        return enterpriseService.getOneEnterprise(id);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    private Map<String, Object> getAllEnterprise() {
        return enterpriseService.getAllEnterprise();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private Map<String, Object> addEnterprise(@RequestBody Map<String, Object> map) {
        return enterpriseService.addEnterprise(map);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    private Map<String, Object> updateEnterprise(@RequestBody Map<String, Object> map) {
        return enterpriseService.updateEnterprise(map);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    private Map<String, Object> deleteEnterprise(@RequestBody Map<String, Object> map) {
        return enterpriseService.deleteEnterprise(map.get("enterpriseId").toString());
    }
}
