package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.MessageDao;
import com.qks.makerSpace.entity.database.Message;
import com.qks.makerSpace.entity.response.MessageRes;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.MessageService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageImpl implements MessageService {

    private final MessageDao messageDao;

    public MessageImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    /**
     * 用户提交一次留言，用于管理员查看
     * @return Hashmap
     */
    @Override
    public Map<String, Object> userPostMessage(JSONObject jsonObject, String token) throws ServiceException {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageText(jsonObject.getString("messageText"));
        message.setMessageTime(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));
        message.setUserId(JWTUtils.parser(token).get("userId").toString());

        if (messageDao.insertMessage(message) < 1)
            throw new ServiceException("留言失败");

        return MyResponseUtil.getResultMap(message.getMessageId(), 0, "success");
    }

    /**
     * 用户查看自己填写的留言（按时间排序）
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getUserMessage(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        List<Message> messageList = messageDao.getUserMessage(userId);

        return MyResponseUtil.getResultMap(messageList, 0, "success");
    }

    /**
     * 管理员查看用户填写的留言
     * @return Hashmap
     */
    @Override
    public Map<String, Object> getAllUserMessage(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        if (!userId.equals("1"))
            throw new ServiceException("请求主体不为管理员");

        List<Message> messageList = messageDao.getAllUserMessage();
        List<MessageRes> data = new ArrayList<>();
        for (Message message : messageList) {
            String messageUserId = message.getUserId();
            String name = messageDao.getNameByUserId(messageUserId).get(0);

            MessageRes messageRes = new MessageRes();
            messageRes.setMessageId(message.getMessageId());
            messageRes.setMessageText(message.getMessageText());
            messageRes.setMessageTime(message.getMessageTime());
            messageRes.setName(name);

            data.add(messageRes);
        }

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 领导查看用户填写的留言
     * @return Hashmap
     */
    @Override
    public Map<String, Object> LeaderGetAllUserMessage(String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        if (!userId.equals("0"))
            throw new ServiceException("请求主体不为领导");

        List<Message> messageList = messageDao.getAllUserMessage();
        List<MessageRes> data = new ArrayList<>();
        for (Message message : messageList) {
            String messageUserId = message.getUserId();
            String name = messageDao.getNameByUserId(messageUserId).get(0);

            MessageRes messageRes = new MessageRes();
            messageRes.setMessageId(message.getMessageId());
            messageRes.setMessageText(message.getMessageText());
            messageRes.setMessageTime(message.getMessageTime());
            messageRes.setName(name);

            data.add(messageRes);
        }

        return MyResponseUtil.getResultMap(data, 0, "success");
    }
}