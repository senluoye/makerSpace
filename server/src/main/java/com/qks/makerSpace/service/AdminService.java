package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AdminService {

    Map<String, Object> getAllDetails();
    Map<String, Object> getTechnologyById(String id);
    Map<String, Object> getSpaceById(String id);
    Map<String, Object> getAllSpaceDetails();
    Map<String, Object> deleteByCreditCode(String id) throws ServiceException;
    Map<String, Object> deleteSpaceById(String id) throws ServiceException;

    // 管理员审核相关服务
    Map<String, Object> agreeTechnologyById(JSONObject map);
    Map<String, Object> disagreeTechnologyById(JSONObject map);
    Map<String, Object> agreeSpaceById(JSONObject map);
    Map<String, Object> disagreeSpaceById(JSONObject map);

    Map<String, Object> getDownLoadForm();
    void downLoadWord(HttpServletResponse response, Map<String , Object> map) throws Exception;
}
