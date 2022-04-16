package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.util.JWTUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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
     * 获取全部已入园审核科技园企业部分信息（没用到）
     * @return map
     */
    @RequestMapping(value = "space/all", method = RequestMethod.GET)
    private Map<String, Object> getSpaceAllDetails() {
        return adminService.getAllSpaceDetails();
    }

    /**
     * 获取最新所有未审核科技园入园申请
     * @return map
     */
    @RequestMapping(value = "applying/technology", method = RequestMethod.GET)
    private Map<String, Object> getAllTechnologyApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getAllTechnologyApplying();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取最新所有未审核众创空间入园申请
     * @return map
     */
    @RequestMapping(value = "applying/space", method = RequestMethod.GET)
    private Map<String, Object> getAllSpaceApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getAllSpaceApplying();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取最新所有已审核科技园入园申请
     * @param httpServletRequest
     * @return map
     * @throws ServiceException
     */
    @RequestMapping(value = "applied/technology", method = RequestMethod.GET)
    private Map<String, Object> getApplied(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getAllTechnologyApplied();
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getAllApplying();
        else throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "oldTechnology/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldTechnologyById(@PathVariable String id) throws ServiceException {
        return adminService.getOldTechnologyById(id);
    }

    /**
     * 获取某一个新企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "newTechnology/{id}", method = RequestMethod.GET)
    private Map<String, Object> getNewTechnologyById(@PathVariable("id") String id) throws ServiceException {
        return adminService.getNewTechnologyById(id);
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
     * 不同意某一个企业科技园申请
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

    /**
     * 获取季度报表所包含的所有年份和季度
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/timelist", method = RequestMethod.GET)
    private Map<String, Object> getFormTimeList(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getFormTimeList();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取某年某个季度全部季度报表（包含未通过和通过）
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/timeform", method = RequestMethod.POST)
    private Map<String, Object> getFormList(HttpServletRequest httpServletRequest,
                                            @RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.getFormList(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取全部未通过的季度报表
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/doubleAudit", method = RequestMethod.GET)
    private Map<String, Object> getFormToAudit(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
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
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("11") || userDescribe.equals("12"))
            return adminService.disagreeFormById(map);
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取所有用户的缴费记录
     * @param map
     * @return
     */
    @RequestMapping(value = "/amount", method = RequestMethod.POST)
    private Map<String, Object> allAmount(HttpServletRequest httpServletRequest) throws ServiceException, IOException {
        return adminService.getAllAmount(httpServletRequest.getHeader("token"));
    }

    /**
     * 获取所有用户的续约记录
     * @param map
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.POST)
    private Map<String, Object> allDemand(HttpServletRequest httpServletRequest) throws ServiceException, IOException {
        return adminService.getAllDemand(httpServletRequest.getHeader("token"));
    }
}
