package com.qks.makerSpace.service;

import com.qks.makerSpace.exception.ServiceException;

import java.util.Map;

public interface NotificationService {
    Map<String, Object> getAllNotice() throws ServiceException;
    Map<String, Object> setNotice(Map<String, Object> map) throws ServiceException;
    Map<String, Object> updateNotice(Map<String, Object> map) throws ServiceException;
    Map<String, Object> deleteNotice(String noticeId) throws ServiceException;
    Map<String, Object> noticeRead(Map<String, Object> map) throws ServiceException;
    Map<String, Object> getInformation(Map<String, Object> map) throws ServiceException;
}
