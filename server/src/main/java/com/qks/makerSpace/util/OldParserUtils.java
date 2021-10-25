package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OldParserUtils {

    /**
     * 获取某个对象
     * @param map
     * @return
     * @throws IllegalAccessException
     */
    public static Old parser(Map<String, Object> map) throws IllegalAccessException {
        Old old = new Old();
        List<OldMainPerson> oldMainPeople = OldMainPersonParser(map.get("oldMainPerson"));
        List<OldProject> oldProjects = OldProjectsParser(map.get("oldProject"));
        List<OldIntellectual> oldIntellectuals = OldIntellectualParser(map.get("oldIntellectual"));
        List<OldFunding> oldFundings = OldFundingParser(map.get("oldFunding"));
        List<OldShareholder> oldShareholders = OldShareholderParser(map.get("oldShareholder"));


        old.setOldId(map.get("id").toString());

        return old;
    }

    public static List<OldShareholder> OldShareholderParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<OldShareholder> resultList = new ArrayList<>();
        String OldShareholderId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            OldShareholder oldShareholder = new OldShareholder();
            oldShareholder.setId(UUID.randomUUID().toString());
            oldShareholder.setOldShareholderId(OldShareholderId);
            oldShareholder.setName(map.get("name").toString());
            oldShareholder.setStake(map.get("stake").toString());
            oldShareholder.setNature(map.get("nature").toString());

            resultList.add(oldShareholder);
        }

        return resultList;
    }

    public static List<OldMainPerson> OldMainPersonParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<OldMainPerson> resultList = new ArrayList<>();
        String OldMainPersonId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            OldMainPerson oldMainPerson = new OldMainPerson();
            oldMainPerson.setId(UUID.randomUUID().toString());
            oldMainPerson.setOldMainpersonId(OldMainPersonId);
            oldMainPerson.setName(map.get("name").toString());
            oldMainPerson.setName(map.get("born").toString());
            oldMainPerson.setName(map.get("job").toString());
            oldMainPerson.setName(map.get("school").toString());
            oldMainPerson.setName(map.get("title").toString());
            oldMainPerson.setName(map.get("background").toString());
            oldMainPerson.setName(map.get("professional").toString());

            resultList.add(oldMainPerson);
        }

        return resultList;
    }

    public static List<OldProject> OldProjectsParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<OldProject> resultList = new ArrayList<>();
        String OldProjectsId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            OldProject oldProject = new OldProject();
            oldProject.setId(UUID.randomUUID().toString());
            oldProject.setOldProjectId(OldProjectsId);
            oldProject.setProject_brief(map.get("projectBrief").toString());
            oldProject.setAdvantage(map.get("advantage").toString());
            oldProject.setMarket(map.get("market").toString());
            oldProject.setEnergy(map.get("energy").toString());
            oldProject.setPollution(map.get("pollution").toString());
            oldProject.setNoise(map.get("noise").toString());
            oldProject.setOthers(map.get("others").toString());

            resultList.add(oldProject);
        }

        return resultList;
    }

    public static List<OldIntellectual> OldIntellectualParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<OldIntellectual> resultList = new ArrayList<>();
        String OldIntellectualId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            OldIntellectual oldIntellectual = new OldIntellectual();
            oldIntellectual.setId(UUID.randomUUID().toString());
            oldIntellectual.setOldIntellectualId(OldIntellectualId);
            oldIntellectual.setName(map.get("name").toString());
            oldIntellectual.setKind(map.get("kind").toString());
            oldIntellectual.setApplyTime(map.get("applyTime").toString());
            oldIntellectual.setApplyTime(map.get("approvalTime").toString());
            oldIntellectual.setIntellectualFile(map.get("intellectualFile").toString());

            resultList.add(oldIntellectual);
        }

        return resultList;
    }

    public static List<OldFunding> OldFundingParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<OldFunding> resultList = new ArrayList<>();
        String OldFundingId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            OldFunding oldFunding = new OldFunding();
            oldFunding.setId(UUID.randomUUID().toString());
            oldFunding.setFundingId(OldFundingId);
            oldFunding.setName(map.get("name").toString());
            oldFunding.setLevel(map.get("level").toString());
            oldFunding.setTime(map.get("time").toString());
            oldFunding.setGrants(map.get("grants").toString());
            oldFunding.setAward(map.get("award").toString());

            resultList.add(oldFunding);
        }

        return resultList;
    }
}
