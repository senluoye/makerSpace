package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.entity.database.Audit;
import com.qks.makerSpace.entity.database.Space;
import com.qks.makerSpace.entity.database.SpacePerson;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SpaceService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceDao spaceDao;

    public SpaceServiceImpl(SpaceDao spaceDao) {
        this.spaceDao = spaceDao;
    }

    /**
     * 加入众创空间
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> joinMakerSpace(JSONObject map, String token) throws ServiceException {
        String userId = JWTUtils.parser(token).get("userId").toString();
        String inApplyId = UUID.randomUUID().toString();

        Space space = new Space(
                inApplyId,
                map.getString("createName"),
                map.getString("applyTime"),
                map.getString("teamNumber"),
                map.getString("describe"),
                map.getString("help")
        );

        JSONArray persons = map.getJSONArray("person");
        List<SpacePerson> personList = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            SpacePerson spacePerson = new SpacePerson(
                    UUID.randomUUID().toString(),
                    inApplyId,
                    persons.getJSONObject(i).getString("personName"),
                    persons.getJSONObject(i).getString("department"),
                    persons.getJSONObject(i).getString("major"),
                    persons.getJSONObject(i).getString("personPhone"),
                    persons.getJSONObject(i).getString("personQq"),
                    persons.getJSONObject(i).getString("personWechat"),
                    persons.getJSONObject(i).getString("note")
            );

            if (spaceDao.addPerson(spacePerson) < 1)
                throw new ServiceException("加入众创空间失败");
        }

        Audit audit = new Audit();
        audit.setAuditId(inApplyId);
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");
        audit.setDescribe("众创空间");

        if (spaceDao.addProject(space) < 1 && spaceDao.addUserSpace(userId, inApplyId) < 1)
            throw new ServiceException("加入众创空间失败");

        if (spaceDao.addAudit(audit) < 1)
            throw new ServiceException("加入众创空间失败");

        Map<String, Object> data = new HashMap<>();
        data.put("inApplyId", inApplyId);
        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    /**
     * 退出众创空间
     * @param map
     * @return Hashmap
     */
    @Override
    public Map<String, Object> quitMakerSpace(JSONObject map) throws ServiceException {
        String inApplyId = map.getString("InApplyId");
        String userId = spaceDao.getUserIdByInApplyId(inApplyId);
        List<String> inApplyIdList = spaceDao.getInApplyIdByUserId(userId);

        for (int i = 0; i < inApplyIdList.size(); i++) {
            try {
                // 先删除关于某一个用户所绑定的所有的Space和SpacePerson数据
                spaceDao.quitSpace(inApplyIdList.get(i));

                // 然后删除审核表里面的数据
                spaceDao.deleteFromAuditByInApplyId(inApplyIdList.get(i));
            } catch (Exception e) {
                throw new ServiceException("退出众创空间失败");
            }
        }

        return MyResponseUtil.getResultMap(inApplyId, 0, "success");
    }
}
