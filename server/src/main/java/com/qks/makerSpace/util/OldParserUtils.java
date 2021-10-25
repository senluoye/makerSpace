package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public static class OldParserUtils {

    public static Old parser(Map<String, Object> map) {
        Old old = new Old();
        List<OldDemand> oldDemands = new ArrayList<>();
        List<OldShareholder> oldShareholders = new ArrayList<>();
        List<OldMainPerson> oldMainPeople = new ArrayList<>();
        List<OldProject> oldProjects = new ArrayList<>();
        List<OldIntellectual> oldIntellectuals = new ArrayList<>();
        List<OldFunding> oldFundings = new ArrayList<>();
        


        old.setOldId(map.get("id").toString());

        return old;
    }

}
