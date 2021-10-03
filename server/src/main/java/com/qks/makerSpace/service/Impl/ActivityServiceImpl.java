package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.ActivityDao;
import com.qks.makerSpace.entity.Activity;
import com.qks.makerSpace.service.ActivityService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张以恒
 * @create 2021/9/18-18:06
 **/
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;

    public ActivityServiceImpl(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Override
    public Map<String, Object> getOneActivity(String id) {
        String activityId = activityDao.getActivityIdByEnterpriseId(id);

        if (activityId != null) {
            Activity activityTemp= activityDao.getOneActivityById(activityId);

            if (activityTemp != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("activity", activityTemp);
                return MyResponseUtil.getResultMap(data, 0, "success");
            } else
                return MyResponseUtil.getResultMap(null, -1, "propertyId doesn't exist");

        } else return MyResponseUtil.getResultMap(null, -1, "activityId doesn't exist");

    }

    @Override
    public Map<String, Object> getAllActivity() {
        List<Activity> list = activityDao.getAllActivity()
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return MyResponseUtil.getResultMap(list, 0, "success");
    }

    @Override
    public Map<String, Object> addActivity(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        String activityId = activityDao.getActivityIdByEnterpriseId(enterpriseId);

        if (activityId == null){
            activityId = UUID.randomUUID().toString();
            map.put("activityId", activityId);

            if (activityDao.addActivity(map) > 0 &&
                    activityDao.updateConnect(enterpriseId, activityId) > 0) {
                return MyResponseUtil.getResultMap(activityId,0,"success");
            } else
                return MyResponseUtil.getResultMap(null,-1,"failure");

        } else return MyResponseUtil.getResultMap(null,-1,"activityId was exist");

    }

    @Override
    public Map<String, Object> updateActivity(Map<String, Object> map) {
        String activityId = map.get("activityId").toString();
        if (activityDao.updateActivity(map) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("activityId",activityId),0,"success");
        else
            return MyResponseUtil.getResultMap(null,-1,"activityId doesn't exist");
    }

    @Override
    public Map<String, Object> deleteActivity(String id) {
        if (activityDao.deleteActivity(id) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("activityId", id), 0, "success");
        else
            return MyResponseUtil.getResultMap(null,-1,"activityId doesn't exist");
    }
}