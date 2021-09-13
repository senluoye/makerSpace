package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.EnterpriseDao;
import com.qks.makerSpace.entity.Enterprise;
import com.qks.makerSpace.service.EnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
            return MyResponseUtil.getResultMap(null, -1, "EnterpriseID doesn't exist");
        else
            return MyResponseUtil.getResultMap(data, 0, "success");
    }

    @Override
    public Map<String, Object> getAllEnterprise() {



        return MyResponseUtil.getResultMap(data, 0, "success");
    }

    @Override
    public Map<String, Object> addEnterprise(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> updateEnterprise(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> deleteEnterprise(String id) {
        return null;
    }
}
