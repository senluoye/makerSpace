package com.qks.makerSpace.controller;

import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    /**
     * 众创空间场地申请
     * @param map
     * @return
     */
    @RequestMapping(value = "space", method = RequestMethod.POST)
    private Map<String, Object> joinMakerSpace(@RequestBody JSONObject map) throws ServiceException {
        return spaceService.joinMakerSpace(map);
    }


    /**
     * 众创空间退出
     * @param map
     * @return
     */
    @RequestMapping(value = "space", method = RequestMethod.DELETE)
    private Map<String, Object> quitMakerSpace(@RequestBody JSONObject map) throws ServiceException {
        return spaceService.quitMakerSpace(map);
    }
}
