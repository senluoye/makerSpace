package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 张以恒
 * @create 2021/9/18-18:45
 **/
//@RestController
//@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    private Map<String ,Object> GetOneActivity(@PathVariable String id) {
        return activityService.getOneActivity(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    private Map<String ,Object> GetAllActivity() {
        return activityService.getAllActivity();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    private Map<String ,Object> AddActivity(@RequestBody Map<String ,Object> map) {
        return activityService.addActivity(map);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    private Map<String ,Object> UpdateActivity(@RequestBody Map<String ,Object> map) {
        return activityService.updateActivity(map);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    private Map<String ,Object> DeleteProperty(@RequestBody Map<String ,Object> map) {
        return activityService.deleteActivity(map.get("activityId").toString());
    }
}