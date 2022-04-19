package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    Map<String, Object> getHomepage(String token) throws ServiceException;
    Map<String, Object> changeAdminEmail(String token, JSONObject json) throws ServiceException;
    Map<String, Object> userApplyForAccount(JSONObject jsonObject) throws ServiceException;
    Map<String, Object> changeUserDetail(JSONObject jsonObject) throws ServiceException;
}
