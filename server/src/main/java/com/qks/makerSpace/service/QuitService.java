package com.qks.makerSpace.service;

import com.qks.makerSpace.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface QuitService {
    Map<String, Object> Quit(Map<String, Object> map) throws ServiceException;

    Map<String, Object> GetQuit(Map<String, Object> map) throws ServiceException;

    Map<String, Object> GetAllQuit(String token) throws ServiceException;

    Map<String, Object> AllQuit(String token) throws ServiceException;

    Map<String, Object> AgreeQuit(String token, Map<String, Object> map) throws ServiceException;

    Map<String, Object> DisagreeQuit(String token, Map<String, Object> map) throws ServiceException;
}
