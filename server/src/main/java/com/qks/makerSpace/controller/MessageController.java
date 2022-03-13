package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.MessageService;
import com.qks.makerSpace.util.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 用户提交一次留言，用于管理员查看
     * @return Hashmap
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    private Map<String, Object> userPostMessage(@RequestBody JSONObject map, HttpServletRequest httpServletRequest) throws ServiceException {
        return messageService.userPostMessage(map, httpServletRequest.getHeader("token"));
    }

    /**
     * 用户查看自己填写的留言（按时间排序）
     * @return Hashmap
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    private Map<String, Object> getUserMessage(HttpServletRequest httpServletRequest) throws ServiceException {
        return messageService.getUserMessage(httpServletRequest.getHeader("token"));
    }

    /**
     * 管理员查看用户填写的留言
     * @return Hashmap
     */
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    private Map<String, Object> getAllUserMessage(HttpServletRequest httpServletRequest) throws ServiceException {
        return messageService.getAllUserMessage(httpServletRequest.getHeader("token"));
    }

    /**
     * 领导查看用户填写的留言
     * @return Hashmap
     */
    @RequestMapping(value = "leader", method = RequestMethod.GET)
    private Map<String, Object> LeaderGetAllUserMessage(HttpServletRequest httpServletRequest) throws ServiceException {
        return messageService.LeaderGetAllUserMessage(httpServletRequest.getHeader("token"));
    }

}
