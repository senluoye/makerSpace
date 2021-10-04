package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.EnterpriseDao;
import com.qks.makerSpace.entity.Enterprise;
import com.qks.makerSpace.service.EnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseDao enterpriseDao;

    public EnterpriseServiceImpl(EnterpriseDao enterpriseDao) {
        this.enterpriseDao = enterpriseDao;
    }

    @Override
    public Map<String, Object> getOneEnterprise(String id) {
        Enterprise data = enterpriseDao.getOneEnterpriseById(id);

        if (data == null)
            return MyResponseUtil.getResultMap(null, -1, "对应企业不存在");
        else
            return MyResponseUtil.getResultMap(data, 0, "success");
    }

    @Override
    public Map<String, Object> getAllEnterprise() {
        List<Enterprise> data = enterpriseDao.getAllEnterprise()
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    @Override
    public Map<String, Object> addEnterprise(Map<String, Object> map) {
        String enterpriseId = UUID.randomUUID().toString();
        map.put("enterpriseId", enterpriseId);
        String teamName = map.get("teamName").toString();
        String connectId = UUID.randomUUID().toString();

        if (enterpriseDao.addEnterprise(map) > 0 &&
                enterpriseDao.addConnect(connectId, teamName, enterpriseId) > 0) {
            return MyResponseUtil.getResultMap(enterpriseId, 0, "success");

        } else return MyResponseUtil.getResultMap(null, -1, "增加企业失败");

    }

    @Override
    public Map<String, Object> updateEnterprise(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        if (enterpriseDao.updateEnterprise(map) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", enterpriseId), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "企业基本数据修改失败");
    }

    @Override
    public Map<String, Object> deleteEnterprise(String id) {
        if (enterpriseDao.deleteEnterpriseAll(id) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", id), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "对应企业不存在");
    }
}
