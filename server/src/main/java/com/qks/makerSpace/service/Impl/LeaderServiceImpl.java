package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.AdminDao;
import com.qks.makerSpace.dao.LeaderDao;
import com.qks.makerSpace.service.AdminService;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.WordChangeUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

@Service
public class LeaderServiceImpl implements LeaderService {

    private final LeaderDao leaderDao;

    public LeaderServiceImpl(LeaderDao leaderDao) {
        this.leaderDao = leaderDao;
    }

    @Override
    public Map<String, Object> getAllOldDetails() {
        return null;
    }

    @Override
    public Map<String, Object> getOldById(String id) {
        return null;
    }

    @Override
    public Map<String, Object> deleteOldById(JSONObject map) {
        return null;
    }

    @Override
    public Map<String, Object> authorization(JSONObject map) {
        return null;
    }


}