package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.database.Place;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlaceDao {

    //获取所有空间情况
    @Select("select * from place")
    List<Place> getAllPlace();

    //获取某空间是否入驻->describe 0 获取未入驻的房间 1 获取已经入驻的房间
    @Select("select * from place where describe = #{describe}")
    List<Place> getDescribePlace(String describe);

    //添加新的房间
    @Insert("insert into place (room,describe) VALUES room = #{room}, describe = #{describe}")
    Integer addPlace(String room, String describe);

    //删除已有房间
    @Select("select * from place where room = #{room}")
    Place getPlaceByName(String room);
    @Delete("delete from place where room = #{room}")
    Integer deletePlace(String room);

    //众创空间分配


}