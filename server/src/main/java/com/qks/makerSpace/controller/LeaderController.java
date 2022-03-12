package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/leader")
public class LeaderController {

    private final LeaderService leaderService;

    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }

    /**
     * 审核授权
     */
    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    private Map<String, Object> authorization(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(leaderService.authorization(map), 0, "success");
    }

    /**
     * 获取全部迁入和独立注册企业的基本信息
     */
    @RequestMapping(value = "old", method = RequestMethod.GET)
    private Map<String, Object> getStatisticalForm() {
        return MyResponseUtil.getResultMap(leaderService.getAllOldDetails(), 0, "success");
    }

    /**
     * 获取某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old/{id}", method = RequestMethod.GET)
    private Map<String, Object> getOldById(@RequestParam("id") String id) {
        return MyResponseUtil.getResultMap(leaderService.getOldById(id), 0, "success");
    }

    /**
     * 删除某一个迁入和独立注册企业入园申请
     * @return
     */
    @RequestMapping(value = "old", method = RequestMethod.DELETE)
    private Map<String, Object> deleteOldById(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(leaderService.deleteOldById(map), 0, "success");
    }

    /**
     * 增加管理员
     * @param map
     * @return
     */
    @RequestMapping(value = "authorization/admin", method = RequestMethod.POST)
    private Map<String, Object> addAdmin(@RequestBody JSONObject map) {
        return MyResponseUtil.getResultMap(leaderService.deleteOldById(map), 0, "success");
    }
}
