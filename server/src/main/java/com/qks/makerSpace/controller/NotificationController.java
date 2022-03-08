package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //获取全部通知
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public Map<String, Object> getAllNotice() throws ServiceException {
        return notificationService.getAllNotice();
    }

    //添加新的通知
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public Map<String, Object> setNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.setNotice(map);
    }

    //修改已有通知
    @RequestMapping(value = "/notice", method = RequestMethod.PUT)
    public Map<String, Object> updateNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.updateNotice(map);
    }

    //删除通知
    @RequestMapping(value = "/notice", method = RequestMethod.DELETE)
    public Map<String, Object> deleteNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.deleteNotice(map.get("noticeId").toString());
    }

}