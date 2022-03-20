package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.NotificationDao;
import com.qks.makerSpace.entity.database.Notification;
import com.qks.makerSpace.entity.response.NoticeResponse;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NotificationService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.apache.xmlbeans.impl.regex.REUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDao notificationDao;

    public NotificationServiceImpl(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Override
    public Map<String, Object> getAllNotice() throws ServiceException {
        List<Notification> list = notificationDao.getTopNotification();
        list.addAll(notificationDao.getCommonNotification());
        if (list.size() != 0) {
            return MyResponseUtil.getResultMap(list,0,"success");
        } else throw new ServiceException("数据被空");
    }

    @Override
    public Map<String, Object> setNotice(Map<String, Object> map) throws ServiceException {
        System.out.println(map);
        String text = map.get("text").toString();
        String forTop = map.get("forTop").toString();
        String title = map.get("title").toString();
        String author = map.get("author").toString();

        Notification notification = new Notification();
        notification.setNoticeId(UUID.randomUUID().toString());
        notification.setTitle(title);
        notification.setAuthor(author);
        notification.setText(text);
        notification.setForTop(forTop);
        Date date = new Date();
        notification.setNoticeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        if (notificationDao.addNotification(notification) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("添加新通知失败");
    }

    @Override
    public Map<String, Object> updateNotice(Map<String, Object> map) throws ServiceException {
        String text = map.get("text").toString();
        String forTop = map.get("forTop").toString();
        String noticeId = map.get("noticeId").toString();
        String title = map.get("title").toString();
        String author = map.get("author").toString();

        Notification notification = new Notification();
        notification.setNoticeId(noticeId);
        notification.setText(text);
        notification.setForTop(forTop);
        notification.setTitle(title);
        notification.setAuthor(author);
        Date date = new Date();
        notification.setNoticeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

        if (notificationDao.updateNotification(notification) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("更新通知失败");
    }

    @Override
    public Map<String, Object> deleteNotice(String noticeId) throws ServiceException {
        if (notificationDao.deleteByNoticeId(noticeId) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("该通知不存在，或已删除");
    }

    @Override
    public Map<String, Object> noticeRead(Map<String, Object> map) throws ServiceException {
        String noticeId = map.get("noticeId").toString();
        String name = map.get("name").toString();

        Notification notification = notificationDao.getDetailNotice(noticeId);
        if (notification == null) {
            throw new ServiceException("该通知不存在");
        }
        ;
        if (notificationDao.selectByNameAndNoticeId(noticeId, name) == null) {
            if (notificationDao.noticeRead(UUID.randomUUID().toString(),noticeId,name) > 0){
                return MyResponseUtil.getResultMap(notification,0,"success");
            } else throw new ServiceException("出现一点小问题");
        } else {
            return MyResponseUtil.getResultMap(notification,0,"success");
        }
    }

    @Override
    public Map<String, Object> getInformation(Map<String, Object> map) throws ServiceException {
        String noticeId = map.get("noticeId").toString();

        Notification notification = notificationDao.getDetailNotice(noticeId);
        if (notification == null) throw new ServiceException("查询通知失败，请重试");

        List<NoticeResponse> read = notificationDao.alreadyRead(noticeId);
        List<NoticeResponse> user = notificationDao.selectUserName();
        user.removeAll(read);

        Map<String, Object> data = new HashMap<>();
        data.put("notice",notification);
        data.put("read",read);
        data.put("noRead",user);

        return MyResponseUtil.getResultMap(data,0,"success");
    }
}