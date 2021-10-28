package com.qks.makerSpace.service;

import java.util.Map;

public interface LoginService {

    Map<String, Object> getAllUser();
    Map<String, Object> AdminOrLeaderLogin(Map<String, Object> map);
    Map<String, Object> oldLogin(Map<String, Object> map);
    Map<String, Object> newLogin(Map<String, Object> map);

}
