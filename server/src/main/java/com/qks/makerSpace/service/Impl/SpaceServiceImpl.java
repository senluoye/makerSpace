package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceDao spaceDao;

    public SpaceServiceImpl(SpaceDao spaceDao) {
        this.spaceDao = spaceDao;
    }

    @Override
    public Map<String, Object> joinMakerSpace(JSONObject map) {



        spaceDao.addProject();

        return null;
    }

    @Override
    public Map<String, Object> quitMakerSpace(JSONObject map) {
        return null;
    }
}
