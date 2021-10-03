package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.PropertyDao;
import com.qks.makerSpace.entity.Property;
import com.qks.makerSpace.service.PropertyService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 张以恒
 * @create 2021/9/18-18:07
 **/
@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyDao propertyDao;

    public PropertyServiceImpl(PropertyDao propertyDao) {
        this.propertyDao = propertyDao;
    }

    @Override
    public Map<String, Object> getOneProperty(String id) {
        String propertyId = propertyDao.getPropertyIdByEnterpriseId(id);

        if (propertyId != null){
            Property propertyTemp = propertyDao.getPropertyById(propertyId);

            if (propertyTemp != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("property",propertyTemp);
                return MyResponseUtil.getResultMap(data,0,"success");
            } else
                return MyResponseUtil.getResultMap(null,-1,"propertyId not exist");

        } else return MyResponseUtil.getResultMap(null,-2,"enterpriseId not exist");

    }

    @Override
    public Map<String, Object> getAllProperty() {
        List<Property> list = propertyDao.getAllProperty()
                .parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return MyResponseUtil.getResultMap(list,0,"success");
    }

    @Override
    public Map<String, Object> addProperty(Map<String, Object> map) {
        String enterpriseId = map.get("enterpriseId").toString();
        String propertyId = propertyDao.getPropertyIdByEnterpriseId(enterpriseId);

        if (propertyId == null){
            propertyId = UUID.randomUUID().toString();
            map.put("propertyId", propertyId);

            if (propertyDao.addProperty(map) > 0 &&
                    propertyDao.updateConnect(enterpriseId, propertyId) > 0) {
                return MyResponseUtil.getResultMap(propertyId,0,"success");

            } else return MyResponseUtil.getResultMap(null,-1,"add failure");

        } else return MyResponseUtil.getResultMap(null,-2,"propertyId was exist or enterpriseId was null");

    }

    @Override
    public Map<String, Object> updateProperty(Map<String, Object> map) {
        String propertyId = map.get("propertyId").toString();
        if (propertyDao.updateProperty(map) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("propertyId",propertyId),0,"success");
        else
            return MyResponseUtil.getResultMap(null,-1,"propertyId doesn't exist");
    }

    @Override
    public Map<String, Object> deleteProperty(String id) {
        if (propertyDao.deleteProperty(id) > 0)
            return MyResponseUtil.getResultMap(new HashMap<>().put("propertyId",id),0,"success");
        else
            return MyResponseUtil.getResultMap(null,-1,"propertyId not exist");
    }
}