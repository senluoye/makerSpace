package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;

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
    Map<String, Object> authorization(JSONObject map);

}
