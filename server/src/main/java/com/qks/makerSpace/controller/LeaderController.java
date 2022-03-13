package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/leader")
public class LeaderController {

    private final LeaderService leaderService;

    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    /**
     * 获取最新入园申请(科技园部分)
     */
    @RequestMapping(value = "authorization/technology", method = RequestMethod.GET)
    private Map<String, Object> authorizationTechnology(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("leader"))
            return leaderService.authorizationTechnology();
        else
            throw new ServiceException("请求主体非管理员");
    }

    /**
     * 获取最新入园申请(众创空间部分)
     */
    @RequestMapping(value = "authorization/space", method = RequestMethod.GET)
    private Map<String, Object> authorizationSpace(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String name = JWTUtils.parser(token).get("name").toString();
        if (name.equals("leader"))
            return leaderService.authorizationSpace();
        else
            throw new ServiceException("请求主体非管理员");
    }


    /**
     * 获取全部迁入和独立注册企业的基本信息
     */
    @RequestMapping(value = "old", method = RequestMethod.GET)
    private Map<String, Object> getStatisticalForm() {
        return MyResponseUtil.getResultMap(leaderService.getAllOldDetails(), 0, "success");
    }

    /**
     * 获取某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldById(@RequestParam("id") String id) {
        return MyResponseUtil.getResultMap(leaderService.getOldById(id), 0, "success");
    }

    /**
     * 删除某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(leaderService.deleteOldById(map), 0, "success");
    }

    /**
     * 增加管理员
     * @param map
     * @return
     */
    @RequestMapping(value = "authorization/admin", method = RequestMethod.POST)
    private Map<String, Object> addAdmin(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(leaderService.deleteOldById(map), 0, "success");
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
        if (name.equals("leader"))
            return leaderService.agreeFormById(map);
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
        if (name.equals("leader"))
            return leaderService.disagreeFormById(map);
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
        if (name.equals("leader"))
            return leaderService.getFormLeaderAudit();
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
        if (name.equals("leader"))
            return leaderService.getFormDetail(map);
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
            return leaderService.getFormByCompany(map);
        else
            throw new ServiceException("请求主体非管理员");
    }
}
