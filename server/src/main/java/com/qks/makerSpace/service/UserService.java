package com.qks.makerSpace.service;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(Map<String, Object> map);
    Map<String, Object> register(Map<String, Object> map);
    Map<String, Object> getAllUser();

}
