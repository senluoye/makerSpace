package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface SpaceService {

    Map<String, Object> joinMakerSpace(JSONObject map) throws ServiceException;
    Map<String, Object> quitMakerSpace(JSONObject map) throws ServiceException;
}
