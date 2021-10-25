package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public static class OldParserUtils {

    public static Old parser(Map<String, Object> map) {
        Old old = new Old();
        List<Map<String, Object>> oldDemands = new ArrayList<>();
        List<Map<String, Object>> oldShareholders = new ArrayList<>();
        List<Map<String, Object>> oldMainPeople = new ArrayList<>();
        List<Map<String, Object>> oldProjects = new ArrayList<>();
        List<Map<String, Object>> oldIntellectuals = new ArrayList<>();
        List<Map<String, Object>> oldFundings = new ArrayList<>();




        old.setOldId(map.get("id").toString());

        return old;
    }

}
