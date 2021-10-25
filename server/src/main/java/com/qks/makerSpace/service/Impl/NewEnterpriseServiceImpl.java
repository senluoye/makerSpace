package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.NewEnterpriseDao;
import com.qks.makerSpace.entity.News;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Service
public class NewEnterpriseServiceImpl implements NewEnterpriseService , Serializable {

    private final NewEnterpriseDao newEnterpriseDao;
    private final JWTUtils jwtUtils;

    public NewEnterpriseServiceImpl(NewEnterpriseDao newEnterpriseDao, JWTUtils jwtUtils) {
        this.newEnterpriseDao = newEnterpriseDao;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 信息状态展示
     * @return
     */
    @Override
    public Map<String, Object> getNewEnterprise() {
        return null;
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> newRegister(Map<String, Object> map) {
        News news = new News();

        String id = UUID.randomUUID().toString();
        news.setNewId(id);
        news.setCreditCode(map.get("creditCode").toString());
        news.setOrganizationCode(map.get("organizationCode").toString());
        news.setPassword(map.get("password").toString());
        news.setName(map.get("name").toString());
        news.setPicture(map.get("picture"));
        news.setRepresent(map.get("represent").toString());
        news.setRepresentCard(map.get("representCard").toString());
        news.setRepresentPhone(map.get("representPhone").toString());
        news.setRepresentEmail(map.get("representEmail").toString());
        news.setAgent(map.get("agent").toString());
        news.setAgentPhone(map.get("agentPhone").toString());
        news.setAgentEmail(map.get("agentEmail").toString());

        if (newEnterpriseDao.newRegister(news) > 0) {
            return MyResponseUtil.getResultMap(id,1,"注册成功");
        } else {
            return MyResponseUtil.getResultMap(null,0,"注册失败");
        }
    }

    @Override
    public Map<String, Object> applyForNewMakerSpace(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> newEnterprisePay(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> updateNewEnterprise(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> quitNewMakerSpace(Map<String, Object> map) {
        return null;
    }
}