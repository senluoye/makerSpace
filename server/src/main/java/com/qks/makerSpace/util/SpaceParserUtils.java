package com.qks.makerSpace.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.exception.ServiceException;

import java.text.SimpleDateFormat;
import java.util.*;

public class SpaceParserUtils {

    public static Space spaceParser(JSONObject jsonObject) {
        Space space = new Space();
        String submitTime = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());

        space.setSpaceId(UUID.randomUUID().toString());
        space.setInApplyId(UUID.randomUUID().toString()); // 先初始化成UUID，后面再修改
        space.setCreateName(jsonObject.getString("createName"));
        space.setApplyTime(jsonObject.getString("applyTime"));
        space.setTeamNumber(jsonObject.getString("teamNumber"));
        space.setDescribe(jsonObject.getString("describe"));
        space.setHelp(jsonObject.getString("help"));
        space.setAccepter(jsonObject.getString("accepter"));
        space.setTime(jsonObject.getString("time"));
        space.setOfficeOpinion(jsonObject.getString("officeOpinion"));
        space.setLeaderOpinion(jsonObject.getString("leaderOpinion"));
        space.setSubmitTime(submitTime);

        return space;
    }

    public static List<SpacePerson> spacePersonParser(Space space, JSONArray jsonArray) {
        List<SpacePerson> spacePersonList = new ArrayList<>();
        String inApplyId = space.getInApplyId();
        String submitTime = space.getSubmitTime();

        for (int i = 0; i < jsonArray.size(); i++) {
            SpacePerson spacePerson = new SpacePerson();

            spacePerson.setSpacePersonId(UUID.randomUUID().toString());
            spacePerson.setInApplyId(inApplyId);
            spacePerson.setPersonName(jsonArray.getJSONObject(i).getString("personName"));
            spacePerson.setDepartment(jsonArray.getJSONObject(i).getString("department"));
            spacePerson.setMajor(jsonArray.getJSONObject(i).getString("major"));
            spacePerson.setPersonPhone(jsonArray.getJSONObject(i).getString("personPhone"));
            spacePerson.setPersonQq(jsonArray.getJSONObject(i).getString("personQq"));
            spacePerson.setPersonWechat(jsonArray.getJSONObject(i).getString("personWechat"));
            spacePerson.setNote(jsonArray.getJSONObject(i).getString("note"));
            spacePerson.setSubmitTime(submitTime);

            spacePersonList.add(spacePerson);
        }

        return spacePersonList;
    }
}
