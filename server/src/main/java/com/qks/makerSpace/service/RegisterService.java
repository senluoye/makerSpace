package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface RegisterService {

    Map<String, Object> addNewUser(JSONObject map) throws ServiceException;

}
