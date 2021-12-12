package com.qks.makerSpace.controller;


import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    @RequestMapping(value = "total", method = RequestMethod.GET)
    private Map<String, Object> getAllPlace() throws ServiceException {
        return placeService.getAllPlace();
    }

    @RequestMapping(value = "describe", method = RequestMethod.GET)
    private Map<String, Object> findDescribePlace(@RequestBody JSONObject map) throws ServiceException {
        return placeService.getDescribePlace(map);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    private Map<String, Object> addPlace(@RequestBody JSONObject map) throws ServiceException {
        return placeService.addPlace(map);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    private Map<String, Object> deletePlace(@RequestBody JSONObject map) throws ServiceException {
        return placeService.addPlace(map);
    }
}