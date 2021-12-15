package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface PlaceService {
    Map<String, Object> getAllPlace() throws ServiceException;
    Map<String, Object> getDescribePlace(JSONObject map) throws ServiceException;
    Map<String, Object> addPlace(JSONObject map) throws ServiceException;
    Map<String, Object> deletePlace(JSONObject map) throws ServiceException;
    Map<String, Object> applyPlace(JSONObject map);
}