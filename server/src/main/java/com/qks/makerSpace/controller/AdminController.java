package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 获取全部迁入和独立注册企业的基本信息
     */
    @RequestMapping(value = "old", method = RequestMethod.GET)
    private Map<String, Object> getStatisticalForm() {
        return MyResponseUtil.getResultMap(adminService.getAllOldDetails(), 0, "success");
    }

    /**
     * 获取某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldById(@RequestParam("id") String id) {
        return MyResponseUtil.getResultMap(adminService.getOldById(id), 0, "success");
    }

    /**
     * 删除某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(adminService.deleteOldById(map), 0, "success");
    }


}
