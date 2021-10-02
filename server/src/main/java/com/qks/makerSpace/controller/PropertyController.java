package com.qks.makerSpace.controller;

import com.qks.makerSpace.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 张以恒
 * @create 2021/9/18-18:45
 **/
@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    private Map<String, Object> GetOneProperty(@PathVariable String id) {
        return propertyService.getOneProperty(id);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    private Map<String, Object> GetAllProperty() {
        return propertyService.getAllProperty();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    private Map<String, Object> AddProperty(@RequestBody Map<String ,Object> map) {
        return propertyService.addProperty(map);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    private Map<String, Object> UpdateProperty(@RequestBody Map<String,Object> map) {
        return propertyService.updateProperty(map);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    private Map<String, Object> DeleteProperty(@RequestBody Map<String, Object> map) {
        return propertyService.deleteProperty(map.get("propertyId").toString());
    }
}