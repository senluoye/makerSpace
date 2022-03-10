package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.SpaceDao;
import com.qks.makerSpace.entity.database.Audit;
import com.qks.makerSpace.entity.database.Space;
import com.qks.makerSpace.entity.database.SpacePerson;
import com.qks.makerSpace.entity.database.UserSpace;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SpaceService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import com.qks.makerSpace.util.SpaceParserUtils;
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

        /**
         * 解析space类
         */
        Space space = SpaceParserUtils.spaceParser(map);

        // 先看看该项目是否已经入驻
        List<Space> spaces = spaceDao.selectSpaceByName(space.getCreateName());
        if (spaces.size() != 0) {
            List<UserSpace> userSpaces = spaceDao.selectUserSpaceById(spaces.get(0).getInApplyId());
            if (userSpaces.size() != 0 && userSpaces.get(0).getUserId().equals(userId))
                // 项目存在并且用户不是自己，则证明该项目已经被其他人入驻
                throw new ServiceException("该项目已被其他用户填报");

            // 自己的项目，则修改space的inAppLyId
            space.setInApplyId(spaces.get(0).getInApplyId());
        }

        /**
         * 在表中新插入数据
         */
        String inApplyId = space.getInApplyId();

        // 解析spacePerson类
        JSONArray persons = map.getJSONArray("person");
        List<SpacePerson> personList = SpaceParserUtils.spacePersonParser(space, persons);

        // 首先插入space表
        if (spaceDao.addProject(space) < 1)
            throw new ServiceException("加入众创空间失败");

        /**
         * 绑定用户和公司
         */
        List<UserSpace> userSpaceList = spaceDao.selectUserSpaceById(userId);
        if (userSpaceList.size() != 0) { // 如果用户之前已经绑定过公司
            // 修改表中的inapplyid
            UserSpace userSpace = new UserSpace();
            userSpace.setUserId(userId);
            userSpace.setInApplyId(inApplyId);
            spaceDao.updateUserSpace(userSpace);
        } else {
            // 否则新插入一条记录
            if (spaceDao.addUserSpace(userId, inApplyId) < 1)
                throw new ServiceException("加入众创空间失败");
        }

        // 循环插入所有soacePerson子表
        for (SpacePerson spacePerson : personList) {
            spaceDao.addPerson(spacePerson);
        }

        /**
         * 加入审核表
         */
        Audit audit = new Audit();
        audit.setAuditId(UUID.randomUUID().toString());
        audit.setDescribe("众创空间");
        audit.setAdministratorAudit("未审核");
        audit.setLeadershipAudit("未审核");
        audit.setSubmitTime(space.getTime());
        audit.setCreditCode(inApplyId);

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
