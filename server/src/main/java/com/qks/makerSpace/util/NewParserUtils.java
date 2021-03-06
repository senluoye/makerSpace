package com.qks.makerSpace.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.entity.database.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class NewParserUtils {

    public static News newsParser(JSONObject map) {
        News news = new News();

        news.setNewId(UUID.randomUUID().toString());
        news.setCreditCode(map.getString("creditCode"));
        news.setName(map.getString("name"));
        news.setRepresent(map.getString("represent"));
        news.setRepresentPerson(map.getString("representPerson"));
        news.setRepresentPhone(map.getString("representPhone"));
        news.setRepresentEmail(map.getString("representEmail"));
        news.setAgent(map.getString("agent"));
        news.setAgentPhone(map.getString("agentPhone"));
        news.setAgentEmail(map.getString("agentEmail"));
        news.setRegisterCapital(map.getString("registerCapital"));
        news.setRealCapital(map.getString("realCapital"));
        news.setOriginNumber(map.getString("originNumber"));
        news.setRegisterTime(map.getString("registerTime"));
        news.setNature(map.getString("nature"));
        news.setInvolved(map.getString("involved"));
        news.setMainBusiness(map.getString("mainBusiness"));
        news.setBusiness(map.getString("business"));
        news.setCooperation(map.getString("cooperation"));

        return news;
    }

    public static List<NewShareholder> NewShareholdersParser(JSONArray obj){
        List<NewShareholder> resultList = new ArrayList<>();
        String NewShareholderId = UUID.randomUUID().toString();

        for (int i = 0; i < obj.size(); i++) {
            NewShareholder newShareholder = new NewShareholder();

            newShareholder.setId(UUID.randomUUID().toString());
            newShareholder.setNewShareholderId(NewShareholderId);
            newShareholder.setName(obj.getJSONObject(i).getString("name"));
            newShareholder.setStake(obj.getJSONObject(i).getString("stake"));
            newShareholder.setNature(obj.getJSONObject(i).getString("nature"));

            resultList.add(newShareholder);
        }
        return resultList;
    }

    public static List<NewMainPerson> NewMainPersonParser(JSONArray obj){
        List<NewMainPerson> resultList = new ArrayList<>();
        String NewMainPersonId = UUID.randomUUID().toString();

        for (int i = 0; i < obj.size(); i++) {
            NewMainPerson newMainPerson = new NewMainPerson();

            newMainPerson.setId(UUID.randomUUID().toString());
            newMainPerson.setNewMainpersonId(NewMainPersonId);
            newMainPerson.setName(obj.getJSONObject(i).getString("name"));
            newMainPerson.setBorn(obj.getJSONObject(i).getString("born"));
            newMainPerson.setJob(obj.getJSONObject(i).getString("job"));
            newMainPerson.setSchool(obj.getJSONObject(i).getString("school"));
            newMainPerson.setTitle(obj.getJSONObject(i).getString("title"));
            newMainPerson.setBackground(obj.getJSONObject(i).getString("background"));
            newMainPerson.setProfessional(obj.getJSONObject(i).getString("professional"));

            resultList.add(newMainPerson);
        }

        return resultList;
    }

    public static List<NewProject> NewProjectParser(JSONArray obj) {
        List<NewProject> resultList = new ArrayList<>();
        String NewProjectId = UUID.randomUUID().toString();

        for (int i = 0; i < obj.size(); i++) {
            NewProject newProject = new NewProject();

            newProject.setId(UUID.randomUUID().toString());
            newProject.setNewProjectId(NewProjectId);
            newProject.setProjectBrief(obj.getJSONObject(i).getString("projectBrief"));
            newProject.setAdvantage(obj.getJSONObject(i).getString("advantage"));
            newProject.setMarket(obj.getJSONObject(i).getString("market"));
            newProject.setEnergy(obj.getJSONObject(i).getString("energy"));
            newProject.setPollution(obj.getJSONObject(i).getString("pollution"));
            newProject.setNoise(obj.getJSONObject(i).getString("noise"));
            newProject.setOthers(obj.getJSONObject(i).getString("others"));

            resultList.add(newProject);
        }

        return resultList;
    }

    public static List<NewIntellectual> NewIntellectualParser(JSONArray obj){
        List<NewIntellectual> resultList = new ArrayList<>();
        String NewIntellectualId = UUID.randomUUID().toString();

        for (int i = 0; i < obj.size(); i++) {
            NewIntellectual newIntellectual = new NewIntellectual();

            newIntellectual.setId(UUID.randomUUID().toString());
            newIntellectual.setNewIntellectualId(NewIntellectualId);
            newIntellectual.setName(obj.getJSONObject(i).getString("name"));
            newIntellectual.setKind(obj.getJSONObject(i).getString("kind"));
            newIntellectual.setApplyTime(obj.getJSONObject(i).getString("applyTime"));
            newIntellectual.setApprovalTime(obj.getJSONObject(i).getString("approvalTime"));

            resultList.add(newIntellectual);
        }
        return resultList;
    }

    public static Demand DemandParser(String obj) {
        JSONObject json = JSONObject.parseObject(obj);

        Demand demand = new Demand();
        demand.setId(UUID.randomUUID().toString());
        demand.setLeaseArea(json.getString("leaseArea"));
        demand.setPosition(json.getString("position"));
        demand.setLease(json.getString("lease"));
        demand.setFloor(json.getString("floor"));
        demand.setElectric(json.getString("electric"));
        demand.setWater(json.getString("water"));
        demand.setWeb(json.getString("web"));
        demand.setOthers(json.getString("others"));

        demand.setTime(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));

        return demand;
    }

    public static NewDemand newDemandParser(String obj) {
        JSONObject json = JSONObject.parseObject(obj);

        NewDemand newDemand = new NewDemand();
        newDemand.setId(UUID.randomUUID().toString());
        newDemand.setNewDemandId(UUID.randomUUID().toString());
        newDemand.setLeaseArea(json.getString("leaseArea"));
        newDemand.setPosition(json.getString("position"));
        newDemand.setLease(json.getString("lease"));
        newDemand.setFloor(json.getString("floor"));
        newDemand.setElectric(json.getString("electric"));
        newDemand.setWater(json.getString("water"));
        newDemand.setWeb(json.getString("web"));
        newDemand.setOthers(json.getString("others"));

        newDemand.setTime(new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date()));

        return newDemand;
    }

    public static Map<String, Object> NewGetResponse(News news) {
        Map<String, Object> result = new HashMap<>();

        result.put("creditCode",news.getCreditCode());
        result.put("name",news.getName());
        result.put("picture",news.getPicture());
        result.put("represent",news.getRepresent());
        result.put("representCard",news.getRepresentCard());
        result.put("representPerson",news.getRepresentPerson());
        result.put("representPhone",news.getRepresentPhone());
        result.put("representEmail",news.getRepresentEmail());
        result.put("agent",news.getAgent());
        result.put("agentPhone",news.getAgentPhone());
        result.put("agentEmail",news.getAgentEmail());
        result.put("registerCapital",news.getRegisterCapital());
        result.put("realCapital",news.getRealCapital());
        result.put("originNumber",news.getOriginNumber());
        result.put("registerTime",news.getRegisterTime());
        result.put("nature",news.getNature());
        result.put("certificate",news.getCertificate());
        result.put("involved",news.getInvolved());
        result.put("mainBusiness",news.getMainBusiness());
        result.put("business",news.getBusiness());
        result.put("cooperation",news.getCooperation());


        return result;
    }
}