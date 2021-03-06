package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/leader")
public class LeaderController {

    private final LeaderService leaderService;

    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    /**
     * 获取最新所有未审核科技园入园申请
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "applying/technology", method = RequestMethod.GET)
    private Map<String, Object> getAllTechnologyApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("0"))
            return leaderService.getAllTechnologyApplying();
        else
            throw new ServiceException("请求主体非领导");
    }

    /**
     * 获取最新所有已审核科技园入园申请
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "applied/technology" , method = RequestMethod.GET)
    private Map<String, Object> getApplied(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("0"))
            return leaderService.getAllTechnologyApplied();
        else
            throw new ServiceException("请求主体非领导");
    }

    /**
     * 获取所有科技园入园申请信息缩略版（包含审核与未审核）
     * @param httpServletRequest
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "applying/all", method = RequestMethod.GET)
    private Map<String, Object> getAllApplying(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("0"))
            return leaderService.getAllApplying();
        else throw new ServiceException("请求主体非领导");
    }

    /**
     * 获取某一个旧企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "oldTechnology/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldTechnologyById(@PathVariable String id) throws ServiceException {
        return leaderService.getOldTechnologyById(id);
    }

    /**
     * 获取某一个新企业入园申请
     * @return HashMap
     */
    @RequestMapping(value = "newTechnology/{id}", method = RequestMethod.GET)
    private Map<String, Object> getNewTechnologyById(@PathVariable String id) throws ServiceException {
        return leaderService.getNewTechnologyById(id);
    }


    /**
     * 获取最新入园申请(众创空间部分)
     */
    @RequestMapping(value = "authorization/space", method = RequestMethod.GET)
    private Map<String, Object> authorizationSpace(HttpServletRequest httpServletRequest) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("0"))
            return leaderService.authorizationSpace();
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.agreeFormById(map);
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.disagreeFormById(map);
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.getFormLeaderAudit();
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.getFormDetail(map);
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.getFormByCompany(map);
        else
            throw new ServiceException("请求主体非领导");
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
        if (userDescribe.equals("0"))
            return leaderService.getFormTimeList();
        else
            throw new ServiceException("请求主体非领导");
    }

    /**
     * 获取某年某个季度全部季度报表（包含未通过和通过）
     * @param httpServletRequest
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "form/timeform", method = RequestMethod.POST)
    private Map<String, Object> getFormList(HttpServletRequest httpServletRequest,
                                            @RequestBody JSONObject map) throws ServiceException {
        String token = httpServletRequest.getHeader("token");
        String userDescribe = JWTUtils.parser(token).get("userDescribe").toString();
        if (userDescribe.equals("0"))
            return leaderService.getFormList(map);
        else
            throw new ServiceException("请求主体非领导");
    }

    /**
     * 同意某一个企业科技园申请
     * @return HashMap
     */
    @RequestMapping(value = "technology/notarize", method = RequestMethod.POST)
    private Map<String, Object> agreeTechnologyById(@RequestBody JSONObject map) throws ServiceException {
        return leaderService.agreeTechnologyById(map);
    }

    /**
     * 不同意某一个企业科技园申请
     * @return HashMap
     */
    @RequestMapping(value = "technology/countermand", method = RequestMethod.POST)
    private Map<String, Object> disagreeTechnologyById(@RequestBody JSONObject map) throws ServiceException {
        return leaderService.disagreeTechnologyById(map);
    }

    /**
     * 获取所有用户的缴费记录
     * @param map
     * @return
     */
    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    private Map<String, Object> allAmount(HttpServletRequest httpServletRequest) throws ServiceException, IOException {
        return leaderService.getAllAmount(httpServletRequest.getHeader("token"));
    }
}
