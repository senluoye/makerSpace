package com.qks.makerSpace.service;

import java.util.Map;

public interface LoginService {

    Map<String, Object> leaderLogin(Map<String, Object> map);
    Map<String, Object> adminLogin(Map<String, Object> map);
    Map<String, Object> oldLogin(Map<String, Object> map);
    Map<String, Object> newLogin(Map<String, Object> map);

}
