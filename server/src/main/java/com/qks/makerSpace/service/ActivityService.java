package com.qks.makerSpace.service;

import java.util.Map;

/**
 * @author 张以恒
 * @create 2021/9/18-18:05
 **/
public interface ActivityService {

    Map<String, Object> getOneActivity(String id);
    Map<String, Object> getAllActivity();
    Map<String, Object> addActivity(Map<String ,Object> map);
    Map<String, Object> updateActivity(Map<String ,Object> map);
    Map<String, Object> deleteActivity(String id);
}
