package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class NewParserUtils {

    public static News newsParser(Map<String, Object> map) {
        News news = new News();

        news.setNewId(map.get("id").toString());
        news.setRegisterCapital(map.get("registerCapital").toString());
        news.setRealCapital(map.get("realCapital").toString());
        news.setOriginNumber(map.get("originNumber").toString());
        news.setRegisterTime(map.get("registerTime").toString());
        news.setInvolved(map.get("involved").toString());
        news.setMainBusiness(map.get("mainBusiness").toString());
        news.setBusiness(map.get("business").toString());
        news.setSuggestion(map.get("suggestion").toString());
        news.setNote(map.get("note").toString());

        return news;
    }

    public static List<NewShareholder> NewShareholdersParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj,Object.class);
        List<NewShareholder> resultList = new ArrayList<>();
        String NewShareholderId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            NewShareholder newShareholder = new NewShareholder();
            newShareholder.setId(UUID.randomUUID().toString());
            newShareholder.setNewShareholderId(NewShareholderId);
            newShareholder.setName(map.get("name").toString());
            newShareholder.setStake(map.get("stake").toString());
            newShareholder.setNature(map.get("nature").toString());

            resultList.add(newShareholder);
        }
        return resultList;
    }

    public static List<NewMainPerson> NewMainPersonParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<NewMainPerson> resultList = new ArrayList<>();
        String NewMainPersonId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            NewMainPerson newMainPerson = new NewMainPerson();
            newMainPerson.setId(UUID.randomUUID().toString());
            newMainPerson.setNewMainpersonId(NewMainPersonId);
            newMainPerson.setName(map.get("person").toString());
            newMainPerson.setJob(map.get("job").toString());
            newMainPerson.setSchool(map.get("school").toString());
            newMainPerson.setTitle(map.get("title").toString());
            newMainPerson.setBackground(map.get("background").toString());
            newMainPerson.setProfessional(map.get("professional").toString());

            resultList.add(newMainPerson);
        }

        return resultList;
    }

    public static List<NewProject> NewProjectParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<NewProject> resultList = new ArrayList<>();
        String NewProjectId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            NewProject newProject = new NewProject();
            newProject.setId(UUID.randomUUID().toString());
            newProject.setNewProjectId(NewProjectId);
            newProject.setProjectBrief(map.get("projectBrief").toString());
            newProject.setAdvantage(map.get("advantage").toString());
            newProject.setMarket(map.get("market").toString());
            newProject.setEnergy(map.get("energy").toString());
            newProject.setPollution(map.get("pollution").toString());
            newProject.setNoise(map.get("noise").toString());
            newProject.setOthers(map.get("others").toString());

            resultList.add(newProject);
        }

        return resultList;
    }

    public static List<NewIntellectual> NewIntellectualParser(Object obj) throws IllegalAccessException {
        List<Object> list = ChangeUtils.castList(obj, Object.class);
        List<NewIntellectual> resultList = new ArrayList<>();
        String NewIntellectualId = UUID.randomUUID().toString();

        for (Object tempObj : list) {
            Map<String, Object> map = ChangeUtils.getObjectToMap(tempObj);

            NewIntellectual newIntellectual = new NewIntellectual();
            newIntellectual.setId(UUID.randomUUID().toString());
            newIntellectual.setNewIntellectualId(NewIntellectualId);
            newIntellectual.setName(map.get("name").toString());
            newIntellectual.setKind(map.get("kind").toString());
            newIntellectual.setApplyTime(map.get("applyTime").toString());
            newIntellectual.setApplyTime(map.get("approvalTime").toString());

            resultList.add(newIntellectual);
        }

        return resultList;
    }

}