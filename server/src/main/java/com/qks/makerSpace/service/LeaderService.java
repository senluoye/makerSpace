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

    Map<String, Object> getAllOldDetails();
    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);

    Map<String, Object> agreeFormById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeFormById(JSONObject map) throws ServiceException;

    Map<String, Object> getFormLeaderAudit() throws ServiceException;
    Map<String, Object> getFormDetail(JSONObject map) throws ServiceException;
    Map<String, Object> getFormByCompany(JSONObject map) throws ServiceException;

}
