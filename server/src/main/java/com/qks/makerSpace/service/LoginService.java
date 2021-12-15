package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface LoginService {
    Map<String, Object> LeaderLogin(Map<String, Object> map) throws ServiceException;
    Map<String, Object> AdminLogin(Map<String, Object> map) throws ServiceException;
    Map<String, Object> CommonLogin(JSONObject map) throws ServiceException;
}
