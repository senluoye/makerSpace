package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.NewEnterpriseDao;
import com.qks.makerSpace.entity.News;
import com.qks.makerSpace.service.NewEnterpriseService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class NewEnterpriseServiceImpl implements NewEnterpriseService , Serializable {

    private final NewEnterpriseDao newEnterpriseDao;

    public NewEnterpriseServiceImpl(NewEnterpriseDao newEnterpriseDao) {
        this.newEnterpriseDao = newEnterpriseDao;
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
    public Map<String, Object> newRegister(Map<String, Object> map, MultipartFile file) {
        byte[] pic = new byte[0];
        try {
            InputStream is = file.getInputStream();
            pic = new byte[(int)file.getSize()];
            is.read(pic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        News news = new News();

        String newId = UUID.randomUUID().toString();
        news.setNewId(newId);
        news.setCreditCode(map.get("creditCode").toString());
        news.setOrganizationCode(map.get("organizationCode").toString());
        news.setPassword(map.get("password").toString());
        news.setName(map.get("name").toString());
        news.setPicture(pic);
        news.setRepresent(map.get("represent").toString());
        news.setRepresentCard(map.get("representCard").toString());
        news.setRepresentPhone(map.get("representPhone").toString());
        news.setRepresentEmail(map.get("representEmail").toString());
        news.setAgent(map.get("agent").toString());
        news.setAgentPhone(map.get("agentPhone").toString());
        news.setAgentEmail(map.get("agentEmail").toString());

        if (newEnterpriseDao.newRegister(news) > 0) {
            return MyResponseUtil.getResultMap(new HashMap<String, Object>().put("id",newId),1,"注册成功");
        } else {
            return MyResponseUtil.getResultMap(null,0,"注册失败");
        }
    }

    @Override
    public Map<String, Object> NewApplyForMakerSpace(Map<String, Object> map) {
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