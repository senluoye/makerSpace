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
     * 获取全部科技园企业的申请信息
     */
    @RequestMapping(value = "/technology/all", method = RequestMethod.GET)
    private Map<String, Object> getTechnologyAllDetails() {
        return adminService.getAllDetails();
    }

    /**
     * 获取全部众创空间企业的申请信息
     */
    @RequestMapping(value = "/space/all", method = RequestMethod.GET)
    private Map<String, Object> getSpaceAllDetails() {
        return adminService.getAllSpaceDetails();
    }

    /**
     * 获取某一个企业入园申请
     * @param creditCode
     * @return
     */
    @RequestMapping(value = "technology/{creditCode}", method = RequestMethod.GET)
    private Map<String, Object> getCompanyById(@PathVariable String creditCode) {
        return adminService.getTechnologyById(creditCode);
    }

    /**
     * 获取某一个企业众创空间申请
     * @param inApplyId
     * @return
     */
    @RequestMapping(value = "space/{inApplyId}", method = RequestMethod.GET)
    private Map<String, Object> getSpaceById(@PathVariable String inApplyId) {
        return adminService.getSpaceById(inApplyId);
    }

    /**
     * 删除某一个企业入园申请
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@RequestBody JSONObject map) {
        return adminService.deletetechnologyById(map.getString("creditCode"));
    }

    /**
     * 删除某一个企业众创空间申请
     * @return
     */
    @RequestMapping(value = "space", method = RequestMethod.DELETE)
    private Map<String, Object> deleteSpaceById(@RequestBody JSONObject map) {
        return adminService.deleteSpaceById(map.getString("inApplyId"));
    }

    /**
     * 同意某一个企业众创空间申请
     * @return
     */
    @RequestMapping(value = "technology", method = RequestMethod.POST)
    private Map<String, Object> agreeById(@RequestBody JSONObject map) {
        return adminService.agreeById(map);
    }

    /**
     * 导出表
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/form/situation", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response) throws Exception {
        adminService.downLoadWord(response, adminService.getDownLoadForm());
    }
}
