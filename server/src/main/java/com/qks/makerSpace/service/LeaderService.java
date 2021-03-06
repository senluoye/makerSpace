package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface LeaderService {

    /**
     * 获取申请记录
     * @return
     */
    Map<String, Object> authorizationTechnology();
    Map<String, Object> authorizationSpace();

    Map<String, Object> getFormLeaderAudit() throws ServiceException;
    Map<String, Object> getFormDetail(JSONObject map) throws ServiceException;
    Map<String, Object> getFormByCompany(JSONObject map) throws ServiceException;

    Map<String, Object> getAllTechnologyApplying();
    Map<String, Object> getAllTechnologyApplied();
    Map<String, Object> getAllApplying();

    Map<String, Object> getOldTechnologyById(String id) throws ServiceException;
    Map<String, Object> getNewTechnologyById(String id) throws ServiceException;

    Map<String, Object> getFormTimeList();
    Map<String, Object> getFormList(JSONObject map);

    /**
     * 入园审核类操作
     * @param map
     * @return
     */
    Map<String, Object> agreeTechnologyById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException;

    /**
     * 季度报表审核类操作
     * @param map
     * @return
     * @throws ServiceException
     */
    Map<String, Object> agreeFormById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeFormById(JSONObject map) throws ServiceException;

    Map<String, Object> getAllAmount(String token) throws ServiceException;
}
