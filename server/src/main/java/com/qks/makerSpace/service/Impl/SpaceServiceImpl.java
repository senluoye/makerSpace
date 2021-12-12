package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.entity.Space;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SpaceService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceDao spaceDao;

    public SpaceServiceImpl(SpaceDao spaceDao) {
        this.spaceDao = spaceDao;
    }

    @Override
    public Map<String, Object> joinMakerSpace(JSONObject map) throws ServiceException {
        String inApplyId = UUID.randomUUID().toString();

        Space space = new Space(
                inApplyId,
                map.getString("describe"),
                map.getString("createName"),
                map.getString("applyTime"),
                map.getString("teamNumber"),
                map.getString("brief"),
                map.getString("help")
        );

        if (spaceDao.addProject(space) < 1)
            throw new ServiceException("无法加入众创空间");

        return MyResponseUtil.getResultMap(new HashMap<>().put("inApplyId", inApplyId), 0, "success");
    }

    @Override
    public Map<String, Object> quitMakerSpace(JSONObject map) {
        return null;
    }
}
