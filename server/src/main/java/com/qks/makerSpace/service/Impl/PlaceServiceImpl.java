package com.qks.makerSpace.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.dao.PlaceDao;
import com.qks.makerSpace.entity.database.Place;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.PlaceService;
import com.qks.makerSpace.util.MyResponseUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceDao placeDao;

    public PlaceServiceImpl(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @Override
    public Map<String, Object> getAllPlace() throws ServiceException {
        List<Place> placeList = placeDao.getAllPlace();

        if( placeList != null) {
            return MyResponseUtil.getResultMap(placeList,0,"success");
        } else throw new ServiceException("查询房间失败");
    }

    @Override
    public Map<String, Object> getDescribePlace(JSONObject map) throws ServiceException {
        String describe = map.get("describe").toString();
        List<Place> placeList = placeDao.getDescribePlace(describe);

        if ( placeList != null) {
            return MyResponseUtil.getResultMap(placeList,0,"success");
        } else throw new ServiceException("查询指定房间错误");
    }

    @Override
    public Map<String, Object> addPlace(JSONObject map) throws ServiceException {
        String describe = "0";
        String room = map.get("room").toString();
        if ( placeDao.addPlace(room,describe) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("添加房间失败");
    }

    @Override
    public Map<String, Object> deletePlace(JSONObject map) throws ServiceException {
        String room = map.get("room").toString();
        if ( placeDao.getPlaceByName(room) == null)
            throw new ServiceException("删除房间不存在");
        if ( placeDao.deletePlace(room) > 0) {
            return MyResponseUtil.getResultMap(null,0,"success");
        } else throw new ServiceException("删除房间失败");
    }

}