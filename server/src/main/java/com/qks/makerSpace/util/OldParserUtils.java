package com.qks.makerSpace.util;

import com.qks.makerSpace.entity.*;

import java.sql.Timestamp;
import java.util.*;

public class OldParserUtils {

    /**
     * 获取某个对象
     * @param map
     * @return
     */
    public static Old parser(Map<String, Object> map) {
        Old old = new Old();
//        map.forEach((key, value) -> {
//
//        });
        old.setOldId(map.get("id").toString());
        old.setRegisterAddress(map.get("registerAddress").toString());
        old.setRegisterCapital(map.get("registerCapital").toString());
        old.setRealAddress(map.get("realAddress").toString());
        old.setRealCapital(map.get("realCapital").toString());
        old.setLastIncome(map.get("lastIncome").toString());
        old.setLastTax(map.get("lastTax").toString());
        old.setEmployees(map.get("employees").toString());
        old.setOriginNumber(map.get("originNumber").toString());
        old.setSetDate((Timestamp) map.get("setDate"));
        old.setNature(map.get("nature").toString());
        old.setInvolved(map.get("involved").toString());
        old.setMainBusiness(map.get("mainBusiness").toString());
        old.setWay(map.get("way").toString());
        old.setCooperation(map.get("cooperation").toString());
        old.setSuggestion(map.get("suggestion").toString());
        old.setNote(map.get("note").toString());

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
            oldMainPerson.setBorn(map.get("born").toString());
            oldMainPerson.setJob(map.get("job").toString());
            oldMainPerson.setSchool(map.get("school").toString());
            oldMainPerson.setTitle(map.get("title").toString());
            oldMainPerson.setBackground(map.get("background").toString());
            oldMainPerson.setProfessional(map.get("professional").toString());

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

    public static Map<String, Object> OldGetResponse(Old old){
        Map<String, Object> result = new HashMap<>();
        result.put("registerAddress", old.getRegisterAddress());
        result.put("license", old.getLicense());
        result.put("registerCapital", old.getRegisterCapital());
        result.put("realAddress", old.getRealAddress());
        result.put("realCapital", old.getRealCapital());
        result.put("lastIncome", old.getLastIncome());
        result.put("lastTax", old.getLastTax());
        result.put("employees", old.getEmployees());
        result.put("originNumber", old.getOriginNumber());
        result.put("setDate", old.getSetDate());
        result.put("nature", old.getNature());
        result.put("certificate", old.getCertificate());
        result.put("involved", old.getInvolved());
        result.put("mainBusiness", old.getMainBusiness());
        result.put("way", old.getWay());
        result.put("business", old.getBusiness());

        return result;
    }
}
