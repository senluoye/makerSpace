package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminService {

    Map<String, Object> getAllOldDetails();
    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);
    Map<String, Object> getAllDetails();
    Map<String, Object> getTechnologyById(String id);
    Map<String, Object> getSpaceById(String id);
    Map<String, Object> getAllSpaceDetails();
    Map<String, Object> deletetechnologyById(String id);
    Map<String, Object> deleteSpaceById(String id);
    Map<String, Object> getDownLoadForm();
    Map<String, Object> agreeById(JSONObject map);
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
}
