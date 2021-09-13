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
            return MyResponseUtil.getResultMap(null, -1, "EnterpriseID doesn't exist");
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
        if (enterpriseDao.addEnterprise(
                map.get("teamName").toString(),
                map.get("head").toString(),
                map.get("phone").toString(),
                map.get("joinTime").toString(),
                map.get("teamNumber").toString(),
                map.get("characteristic").toString(),
                map.get("kind").toString(),
                map.get("field").toString(),
                map.get("achievements").toString(),
                map.get("scope").toString(),
                map.get("income").toString(),
                map.get("tax").toString(),
                map.get("preferentialTax").toString(),
                map.get("taxFree").toString(),
                map.get("support").toString(),
                map.get("supportAmount").toString(),
                map.get("riskInvestment").toString(),
                map.get("investmentAmount").toString(),
                map.get("cooperation").toString(),
                map.get("projectName").toString(),
                map.get("projectAmount").toString(),
                map.get("highTec").toString(),
                map.get("tecSme").toString()
        ) >= 1)
            return MyResponseUtil.getResultMap(enterpriseId, 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "teamName doesn't exist");
    }

    @Override
    public Map<String, Object> updateEnterprise(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        if (enterpriseDao.updateEnterprise(
                enterpriseId,
                map.get("teamName").toString(),
                map.get("head").toString(),
                map.get("phone").toString(),
                map.get("joinTime").toString(),
                map.get("teamNumber").toString(),
                map.get("characteristic").toString(),
                map.get("kind").toString(),
                map.get("field").toString(),
                map.get("achievements").toString(),
                map.get("scope").toString(),
                map.get("income").toString(),
                map.get("tax").toString(),
                map.get("preferentialTax").toString(),
                map.get("taxFree").toString(),
                map.get("support").toString(),
                map.get("supportAmount").toString(),
                map.get("riskInvestment").toString(),
                map.get("investmentAmount").toString(),
                map.get("cooperation").toString(),
                map.get("projectName").toString(),
                map.get("projectAmount").toString(),
                map.get("highTec").toString(),
                map.get("tecSme").toString()
        ) >= 1)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", enterpriseId), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "enterpriseID doesn't exist");
    }

    @Override
    public Map<String, Object> deleteEnterprise(String id) {
        if (enterpriseDao.deleteEnterprise(id) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("enterpriseId", id), 0, "success");
        else
            return MyResponseUtil.getResultMap(null, -1, "enterpriseID doesn't exist");
    }
}
