package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.LeaderDao;
import com.qks.makerSpace.entity.request.LeaderSpaceApplyingReq;
import com.qks.makerSpace.entity.request.LeaderTechnologyApplyingReq;
import com.qks.makerSpace.entity.request.AdminSpaceApplyingReq;
import com.qks.makerSpace.service.LeaderService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LeaderServiceImpl implements LeaderService {

    private final LeaderDao leaderDao;

    public LeaderServiceImpl(LeaderDao leaderDao) {
        this.leaderDao = leaderDao;
    }

    /**
     * 获取科技园最新入园申请
     * @return
     */
    @Override
    public Map<String, Object> authorizationTechnology() {
        List<LeaderTechnologyApplyingReq> lists = leaderDao.getAllTechnologyApplying();
        for (LeaderTechnologyApplyingReq applyingReq : lists) {
            // 首先看看该公司在不在旧企业表中
            List<String> oldNameList = leaderDao.getOldNameByCreditCode(applyingReq.getCreditCode());
            if (oldNameList.size() > 0) // 不为0，在旧企业中
                applyingReq.setName(oldNameList.get(0));
            else {
                List<String> newNameList = leaderDao.getNewNameByCreditCode(applyingReq.getCreditCode());
                applyingReq.setName(newNameList.get(0));
            }
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
    }

    /**
     * 获取众创空间最新入园申请
     * @return
     */
    @Override
    public Map<String, Object> authorizationSpace() {
        List<LeaderSpaceApplyingReq> lists = leaderDao.getAllSpaceApplying();
        for (LeaderSpaceApplyingReq spaceApplyingReq : lists) {
            String inApplyId =  spaceApplyingReq.getInApplyId();
            String name = leaderDao.getSpaceNameByCreditCode(inApplyId).get(0);
            spaceApplyingReq.setName(name);
        }
        return MyResponseUtil.getResultMap(lists, 0, "success");
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