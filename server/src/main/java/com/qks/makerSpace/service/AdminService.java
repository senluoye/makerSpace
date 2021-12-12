package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminService {

    Map<String, Object> getAllOldDetails();
    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);

    Map<String, Object> getDownLoadForm();
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
}
