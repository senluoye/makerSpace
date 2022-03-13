package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MessageService {
    Map<String, Object> userPostMessage(JSONObject jsonObject, String token) throws ServiceException;
    Map<String, Object> getUserMessage(String token) throws ServiceException;
    Map<String, Object> getAllUserMessage(String token) throws ServiceException;
    Map<String, Object> LeaderGetAllUserMessage(String token) throws ServiceException;
}
