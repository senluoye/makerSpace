package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Property;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PropertyDao {

    Property getPropertyByID(String id);
    List<Property> getAllProperty();
    Integer addProperty(Map<String,Object> map);
    Integer updateConnect(String enterpriseId, String propertyId);
    Integer deleteProperty(String id);
    Integer updateProperty(Map<String,Object> map);
}
