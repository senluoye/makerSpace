package com.qks.makerSpace.service;

import java.util.Map;

/**
 * @author 张以恒
 * @create 2021/9/18-17:59
 **/
public interface PropertyService {

    Map<String, Object> getOneProperty(String id);
    Map<String, Object> getAllProperty();
    Map<String, Object> addProperty(Map<String, Object> map);
    Map<String, Object> updateProperty(Map<String, Object> map);
    Map<String, Object> deleteProperty(String id);

}
