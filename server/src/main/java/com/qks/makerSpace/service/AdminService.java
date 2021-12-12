package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface AdminService {

<<<<<<< HEAD
    Map<String, Object> getAllOldDetails();
    Map<String, Object> getOldById(String id);
    Map<String, Object> deleteOldById(JSONObject map);
=======
    Map<String, Object> getAllDetails();
    Map<String, Object> gettechnologyById(String id);
    Map<String, Object> getSpaceById(String id);
    Map<String, Object> deletetechnologyById(String id);
    Map<String, Object> deleteSpaceById(String id);
    Map<String, Object> getDownLoadForm();
    Map<String, Object> agreeById(JSONObject map);
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
>>>>>>> bef245fdc77bbd206b518da86908ca94fef5e2d7
}
