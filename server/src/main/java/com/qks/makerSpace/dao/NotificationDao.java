package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Notification;
import com.qks.makerSpace.entity.response.NoticeResponse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface NotificationDao {

    @Insert("insert into notification(notice_id, title, author, text, for_top, notice_time) " +
            "values (#{noticeId}, #{title}, #{author}, #{text}, #{forTop}, #{noticeTime})")
    Integer addNotification(Notification notification);

    @Update("update notification set text = #{text}, title = #{title}, author = #{author}, for_top = #{forTop}, notice_time = #{noticeTime} " +
            "where notice_id = #{noticeId}")
    Integer updateNotification(Notification notification);

    @Delete("delete from notification where notice_id = #{noticeId}")
    Integer deleteByNoticeId(String noticeId);

    @Select("select * from notification where for_top = '1' order by notice_time desc")
    List<Notification> getTopNotification();

    @Select("select * from notification where for_top = '0' order by notice_time desc")
    List<Notification> getCommonNotification();

    @Select("select * from notification where notice_id = #{noticeId}")
    Notification getDetailNotice(String noticeId);

    @Insert("insert into notice_read(id, notice_id, name) " +
            "values (#{id}, #{noticeId}, #{name})")
    Integer noticeRead(String id, String noticeId, String name);

    @Select("select name from notice_read where notice_id = #{noticeId}")
    List<NoticeResponse> alreadyRead(String noticeId);

    @Select("select id from notice_read where notice_id = #{noticeId} and name = #{name}")
    String selectByNameAndNoticeId(String noticeId, String name);

    @Select("select name from user")
    List<NoticeResponse> selectUserName();
}
