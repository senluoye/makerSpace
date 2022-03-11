package com.qks.makerSpace.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {

    Map<String, Object> getHomepage(HttpServletRequest httpServletRequest);
}
