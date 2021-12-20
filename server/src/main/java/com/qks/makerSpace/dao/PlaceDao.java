package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Place;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceDao {

    //获取所有空间情况
    @Select("select * from place")
    List<Place> getAllPlace();

    //获取某空间是否入驻->describe 0 获取未入驻的房间 1 获取已经入驻的房间
    @Select("select * from place where `describe` = #{describe}")
    List<Place> getDescribePlace(String describe);

    //添加新的房间
    @Insert("insert into place(room, `describe`) VALUES (#{room}, #{describe})")
    Integer addPlace(String room,String describe);

    //查找房间
    @Select("select `describe` from place where room = #{rooms}")
    String getPlaceByName(String rooms);

    //删除已有房间
    @Delete("delete from place where room = #{rooms}")
    Integer deletePlace(String rooms);

    //众创空间分配
    @Update("update place set `describe` = #{describe} where room = #{room}")
    Integer updatePlace(String room, String describe);

    @Update("update new set room = #{room} where credit_code = #{creditCode}")
    Integer updateNewRoom(String room, String creditCode);

    @Update("update old set room = #{room} where credit_code = #{creditCode}")
    Integer updateOldRoom(String room, String creditCode);

    @Select("select new_id from new where credit_code = #{creditCode}")
    String selectNew(String creditCode);

    @Select("select old_id from old where credit_code = #{creditCode)")
    String selectOld(String creditCode);

}