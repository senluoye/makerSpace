package com.qks.makerSpace.dao;

import com.qks.makerSpace.entity.Property;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PropertyDao {

    Property getPropertyById(String id);
    List<Property> getAllProperty();
    Map<String, Object> getEnterpriseDetails(String id);
    Integer addProperty(Map<String,Object> map);
    Integer updateConnect(String enterpriseId, String propertyId);
    Integer deleteProperty(String id);
    Integer updateProperty(Map<String,Object> map);
    String getPropertyIdByEnterpriseId(String id);

}
