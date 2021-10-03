package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ActivityDao {

    Activity getOneActivityById(String id);
    List<Activity> getAllActivity();
    Integer addActivity(Map<String,Object> map);
    Integer updateConnect(String enterpriseId, String activityId);
    Integer deleteActivity(String id);
    Integer updateActivity(Map<String,Object> map);
    String getActivityIdByEnterpriseId(String id);

}
