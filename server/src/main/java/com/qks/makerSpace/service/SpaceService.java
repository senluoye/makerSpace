package com.qks.makerSpace.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface SpaceService {

    Map<String, Object> joinMakerSpace(JSONObject map);
    Map<String, Object> quitMakerSpace(JSONObject map);
}
