package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminService {
    Map<String, Object> getRegister();
    Map<String, Object> addNewUser(JSONObject jsonObject) throws ServiceException;

    Map<String, Object> getAllDetails() throws ServiceException;
    Map<String, Object> getNewTechnologyById(String id) throws ServiceException;
    Map<String, Object> getOldTechnologyById(String id) throws ServiceException;
    Map<String, Object> getSpaceById(String id) throws IllegalAccessException;
    Map<String, Object> getAllSpaceDetails();
    Map<String, Object> deleteByCreditCode(String id) throws ServiceException;
    Map<String, Object> deleteSpaceById(String id) throws ServiceException;


    //获取最新的入园审核
    Map<String, Object> getAllTechnologyApplying();
    Map<String, Object> getAllSpaceApplying();

    // 相关服务
    Map<String, Object> agreeTechnologyById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeTechnologyById(JSONObject map) throws ServiceException;
    Map<String, Object> agreeSpaceById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeSpaceById(JSONObject map) throws ServiceException;
    Map<String, Object> agreeFormById(JSONObject map) throws ServiceException;
    Map<String, Object> disagreeFormById(JSONObject map) throws ServiceException;

    //季度报表相关操作
    Map<String, Object> getFormToAudit() throws ServiceException;
    Map<String, Object> getFormLeaderAudit() throws ServiceException;
    Map<String, Object> getAudited() throws ServiceException;
    Map<String, Object> getFormDetail(JSONObject map) throws ServiceException;
    Map<String, Object> getFormByCompany(JSONObject map) throws ServiceException;
    Map<String, Object> deleteForm(JSONObject map) throws ServiceException;


    Map<String, Object> getDownLoadForm();
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
}
