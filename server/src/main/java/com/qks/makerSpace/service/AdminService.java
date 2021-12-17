package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminService {

    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);
    Map<String, Object> getAllDetails();
    Map<String, Object> getTechnologyById(String id);
    Map<String, Object> getSpaceById(String id);
    Map<String, Object> getAllSpaceDetails();
    Map<String, Object> deleteByCreditCode(String id) throws ServiceException;
    Map<String, Object> deleteSpaceById(String id) throws ServiceException;
    Map<String, Object> getDownLoadForm();
    Map<String, Object> agreeById(JSONObject map);
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
}
