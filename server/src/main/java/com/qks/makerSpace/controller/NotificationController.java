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

    /**
     * （企业和管理员）获取全部通知
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public Map<String, Object> getAllNotice() throws ServiceException {
        return notificationService.getAllNotice();
    }

    /**
     * （管理员）添加新的通知
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public Map<String, Object> setNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.setNotice(map);
    }

    /**
     * （管理员）修改已有通知
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/notice", method = RequestMethod.PUT)
    public Map<String, Object> updateNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.updateNotice(map);
    }

    /**
     * （管理员）删除通知
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/notice", method = RequestMethod.DELETE)
    public Map<String, Object> deleteNotice(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.deleteNotice(map.get("noticeId").toString());
    }

    /**
     * （企业）获取某条具体通知，并对公司标记
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/detailNotice", method = RequestMethod.POST)
    public Map<String, Object> noticeRead(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.noticeRead(map);
    }

    /**
     * （管理员）通知内容，未查看企业和已查看企业
     * @param map
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "admin/notice",method = RequestMethod.GET)
    public Map<String, Object> getInformation(@RequestBody Map<String, Object> map) throws ServiceException {
        return notificationService.getInformation(map);
    }

}