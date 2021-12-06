package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.entity.Space;
import com.qks.makerSpace.entity.SpacePerson;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SpaceService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Space space = new Space();
        String inApplyId = UUID.randomUUID().toString();

        space.setInApplyId(inApplyId);
        space.setDescribe(map.getString("describe"));
        space.setCreateName(map.getString("createName"));
        space.setApplyTime(map.getString("applyTime"));
        space.setTeamNumber(map.getString("teamNumber"));
        space.setBrief(map.getString("brief"));
        space.setHelp(map.getString("help"));
        if (spaceDao.addProject(space) < 1)
            throw new ServiceException("信息插入失败");

        JSONArray persons = map.getJSONArray("Person");
        for (int i = 0; i < persons.size(); i++) {
            SpacePerson spacePerson = new SpacePerson(
                    inApplyId,
                    persons.getJSONObject(i).getString("personName"),
                    persons.getJSONObject(i).getString("department"),
                    persons.getJSONObject(i).getString("major"),
                    persons.getJSONObject(i).getString("personPhone"),
                    persons.getJSONObject(i).getString("personQq"),
                    persons.getJSONObject(i).getString("personWechat"),
                    persons.getJSONObject(i).getString("note"));

            if (spaceDao.addPerson(spacePerson) < 1)
                throw new ServiceException("信息插入失败");
        }

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }

    @Override
    public Map<String, Object> quitMakerSpace(JSONObject map) throws ServiceException {

        String inApplyId = map.getString("InApplyId");

        if (spaceDao.quitSpace(inApplyId) < 1)
            throw new ServiceException("信息删除失败或该公司不存在");

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }
}
