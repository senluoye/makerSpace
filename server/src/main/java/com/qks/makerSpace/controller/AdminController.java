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
     * @param inApplyId
     * @return
     */
    @RequestMapping(value = "technology/{inApplyId}", method = RequestMethod.GET)
    private Map<String, Object> getCompanyById(@PathVariable String inApplyId) {
        return adminService.getTechnologyById(inApplyId);
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
     * @param inApplyId
     * @return
     */
    @RequestMapping(value = "technology/{inApplyId}", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@PathVariable String inApplyId) {
        return adminService.deletetechnologyById(inApplyId);
    }

    /**
     * 删除某一个企业众创空间申请
     * @param inApplyId
     * @return
     */
    @RequestMapping(value = "space/{inApplyId}", method = RequestMethod.DELETE)
    private Map<String, Object> deleteSpaceById(@PathVariable String inApplyId) {
        return adminService.deleteSpaceById(inApplyId);
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
     * 这是啥
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/form/situation", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response) throws Exception {
        adminService.downLoadWord(response, adminService.getDownLoadForm());
    }

}
