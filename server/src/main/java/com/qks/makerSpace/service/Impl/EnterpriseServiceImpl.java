package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.EnterpriseDao;
import com.qks.makerSpace.service.EnterpriseService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseDao enterpriseDao;
    private final MyResponseUtil myResponseUtil;

    public EnterpriseServiceImpl(EnterpriseDao enterpriseDao, MyResponseUtil myResponseUtil) {
        this.enterpriseDao = enterpriseDao;
        this.myResponseUtil = myResponseUtil;
    }

    @Override
    public Map<String, Object> getOneEnterprise(String id) {
        Map<String, Object> data = new HashMap<>();



    }

    @Override
    public Map<String, Object> getAllEnterprise() {
        return null;
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
