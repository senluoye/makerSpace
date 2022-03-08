package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Notification;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao {

    @Insert("insert into notification(notice_id, text, for_top, notice_time) " +
            "values (#{noticeId}, #{text}, #{forTop}, #{noticeTime})")
    Integer addNotification(Notification notification);

    @Update("update notification set text = #{text}, for_top = #{forTop}, notice_time = #{noticeTime} " +
            "where notice_id = #{noticeId}")
    Integer updateNotification(Notification notification);

    @Delete("delete from notification where notice_id = #{noticeId}")
    Integer deleteByNoticeId(String noticeId);

    @Select("select * from notification  where for_top = '1' order by notice_time desc")
    List<Notification> getTopNotification();

    @Select("select * from notification where for_top = '0' order by notice_time desc")
    List<Notification> getCommonNotification();
}
