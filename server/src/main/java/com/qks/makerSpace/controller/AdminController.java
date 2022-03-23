package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.JWTUtils;
import lombok.ToString;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员获取用户的账号申请
     * @return Hashmap
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    private Map<String, Object> getRegister(HttpServletRequest httpServletRequest) throws ServiceException {
        return adminService.getRegister();
    }

    /**
     * 管理员分配公司账号
     * @return Hashmap
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    private Map<String, Object> register(@RequestBody JSONObject map) throws ServiceException {
        return adminService.addNewUser(map);
    }

    /**
     * 获取全部已审核科技园企业的部分信息
     */
    @RequestMapping(value = "technology/all", method = RequestMethod.GET)
    private Map<String, Object> getTechnologyAllDetails() throws ServiceException {
        return adminService.getAllDetails();
    }

    /**
     * 获取全部已入园审核科技园企业部分信息
     * @return
     */
    @RequestMapping(value = "space/all", method = RequestMethod.GET)
    private Map<String, Object> getSpaceAllDetails() {
        return adminService.getAllSpaceDetails();
    }

    /**
     * 获取最新所有未审核科技园入园申请
     * @return
     */
    @RequestMapping(value = "applying/technology", method = RequestMethod.GET)
    private Map<String, Object> getAllTechnologyApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getAllTechnologyApplying();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取最新所有未审核众创空间入园申请
     * @return
     */
    @RequestMapping(value = "applying/space", method = RequestMethod.GET)
    private Map<String, Object> getAllSpaceApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getAllSpaceApplying();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取所有科技园入园申请信息缩略版（包含审核与未审核）
     * @return
     */
    @RequestMapping(value = "applying/all", method = RequestMethod.GET)
    private Map<String, Object> getAllApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getAllApplying();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "oldTechnology/", method = RequestMethod.POST)
    private Map<String, Object> getOldTechnologyById(@PathVariable JSONObject map) throws ServiceException {
        return adminService.getOldTechnologyById(map);
    }

    /**
     * 获取某一个新企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "newTechnology/{creditCode}", method = RequestMethod.GET)
    private Map<String, Object> getNewTechnologyById(@PathVariable String creditCode) throws ServiceException {
        return adminService.getNewTechnologyById(creditCode);
    }

    /**
     * 删除某一个企业入园申请
     * @return Hashmap
     */
    @RequestMapping(value = "technology", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldByCreditCode(@RequestBody JSONObject map) throws ServiceException {
        String creditCode = map.getString("creditCode");

        return adminService.deleteByCreditCode(creditCode);
    }

    /**
     * 获取某一个企业众创空间申请
     * @return HashMap
     */
    @RequestMapping(value = "space/{inApplyId}", method = RequestMethod.GET)
    private Map<String, Object> getSpaceById(@PathVariable String inApplyId) throws IllegalAccessException {
        return adminService.getSpaceById(inApplyId);
    }

    /**
     * 删除某一个企业众创空间申请
     * @return HashMap
     */
    @RequestMapping(value = "space", method = RequestMethod.DELETE)
    private Map<String, Object> deleteSpaceById(@RequestBody JSONObject map) throws ServiceException {
        return adminService.deleteSpaceById(map.getString("inApplyId"));
    }

    /**
     * 同意某一个企业科技园申请
     * @return HashMap
     */
    @RequestMapping(value = "technology/notarize", method = RequestMethod.POST)
    private Map<String, Object> agreeTechnologyById(@RequestBody JSONObject map) throws ServiceException {
        return adminService.agreeTechnologyById(map);
    }

    /**
     * 取消某一个企业科技园申请
     * @return HashMap
     */
    @RequestMapping(value = "technology/countermand", method = RequestMethod.POST)
    private Map<String, Object> disagreeTechnologyById(@RequestBody JSONObject map) throws ServiceException {
        return adminService.disagreeTechnologyById(map);
    }

    /**
     * 同意某一个企业众创空间申请
     * @return HashMap
     */
    @RequestMapping(value = "space/notarize", method = RequestMethod.POST)
    private Map<String, Object> agreeSpaceById(@RequestBody JSONObject map) throws ServiceException {
        return adminService.agreeSpaceById(map);
    }

    /**
     * 取消某一个企业众创空间申请
     * @return HashMap
     */
    @RequestMapping(value = "space/countermand", method = RequestMethod.POST)
    private Map<String, Object> disagreeSpaceById(@RequestBody JSONObject map) throws ServiceException {
        return adminService.disagreeSpaceById(map);
    }

//----季度报表操作----
    /**
     * 获取全部未通过的季度报表
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/doubleAudit", method = RequestMethod.GET)
    private Map<String, Object> getFormToAudit(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getFormToAudit();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取领导未通过的季度报表
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/leaderAudit", method = RequestMethod.GET)
    private Map<String, Object> getFormLeaderAudit(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getFormLeaderAudit();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取全部已通过的季度报表
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/audited", method = RequestMethod.GET)
    private Map<String, Object> getAudited(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getAudited();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取某个企业最新的季度报表
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/detail", method = RequestMethod.POST)
    private Map<String, Object> getFormDetail(HttpServletRequest httpServletRequest,@RequestBody JSONObject map) throws ServiceException {

        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getFormDetail(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     *获取某个企业的历史季度报表
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/company", method = RequestMethod.POST)
    private Map<String, Object> getFormByCompany(HttpServletRequest httpServletRequest,@RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.getFormByCompany(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 删除企业的季度报表
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/delete", method = RequestMethod.DELETE)
    private Map<String, Object> deleteForm(HttpServletRequest httpServletRequest,@RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.deleteForm(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取导出表的信息
     */
    @RequestMapping(value = "/form/situation", method = RequestMethod.GET)
    private void getStatisticalForm(HttpServletResponse response) throws Exception {
        adminService.downLoadWord(response, adminService.getDownLoadForm());
    }

    /**
     * 同意某个季度报表
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/form/agree", method = RequestMethod.POST)
    private Map<String, Object> agreeForm(HttpServletRequest httpServletRequest, @RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.agreeFormById(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 不同意某个季度报表
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/disagree", method = RequestMethod.POST)
    private Map<String, Object> disagreeForm(HttpServletRequest httpServletRequest, @RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("admin"))
            return adminService.disagreeFormById(map);
        else
            throw new ServiceException("请求主体非管理员");
    }
}
