package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.entity.request.AdminTechnologyApplyingReq;
import com.qks.makerSpace.entity.response.AdminSpaceSuggestion;
import com.qks.makerSpace.entity.response.AdminSuggestion;
import com.qks.makerSpace.entity.response.AllTechnology;
import com.qks.makerSpace.entity.response.MessageRes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {

    @Insert("insert into message(message_id, user_id, message_text, message_time) " +
            "VALUES (#{messageId}, #{userId}, #{messageText}, #{messageTime})")
    Integer insertMessage(Message message);

    @Select("select * from message where user_id = #{userId} order by message_time desc")
    List<Message> getUserMessage(String userId);

    @Select("select * from message order by message_time desc")
    List<Message> getAllUserMessage();

    @Select("select name from user where user_id = #{userId}")
    List<String> getNameByUserId(String userId);
}
