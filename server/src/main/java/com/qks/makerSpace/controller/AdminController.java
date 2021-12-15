package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.service.AdminService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 获取全部企业的申请信息
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    private Map<String, Object> getAllDetails() {
        return adminService.getAllDetails();
    }

    /**
     * 获取某一个企业入园申请
     * @return
     */
    @RequestMapping(value = "technology/{id}", method = RequestMethod.GET)
    private Map<String, Object> getCompanyById(@PathVariable String id) {
        return adminService.getTechnologyById(id);
    }

    /**
     * 获取某一个企业众创空间申请
     * @return
     */
    @RequestMapping(value = "space/{id}", method = RequestMethod.GET)
    private Map<String, Object> getSpaceById(@PathVariable String id) {
        return adminService.getSpaceById(id);
    }

    /**
     * 删除某一个企业入园申请
     * @return
     */
    @RequestMapping(value = "technology/{id}", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@PathVariable String id) {
        return adminService.deletetechnologyById(id);
    }

    /**
     * 删除某一个企业众创空间申请
     * @return
     */
    @RequestMapping(value = "space/{id}", method = RequestMethod.DELETE)
    private Map<String, Object> deleteSpaceById(@PathVariable String id) {
        return adminService.deleteSpaceById(id);
    }

    /**
     * 同意某一个企业众创空间申请
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.POST)
    private Map<String, Object> agreeById(@RequestBody JSONObject map) {
        return adminService.agreeById(map);
    }


    @RequestMapping(value = "/form/situation", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response) throws Exception {
        adminService.downLoadWord(response, adminService.getDownLoadForm());
    }

}
