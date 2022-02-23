package com.qks.makerSpace.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.FormReq;

import java.util.*;

public class FormParserUtils {
    public static FormReq parser(JSONObject map) {
        FormReq form  = new FormReq();

        form.setTime(map.getString("time"));
        form.setTeamName(map.getString("teamName"));
        form.setCreditCode(map.getString("creditCode"));
        form.setRegisterCapital(map.getString("registerTime"));
        form.setJoinTime(map.getString("joinTime"));
        form.setRegisterCapital(map.getString("registerCapital"));
        form.setRegisterKind(map.getString("registerKind"));
        form.setIndustryKind(map.getString("industryKind"));
        form.setField(map.getString("field"));
        form.setGraduatedEnterprise(map.getString("graduatedEnterprise"));
        form.setGraduatedTime(map.getString("graduatedTime"));
        form.setHighEnterprise(map.getString("highEnterprise"));
        form.setMediumSized(map.getString("mediumSized"));
        form.setHeaderKind(map.getString("headerKind"));
        form.setSerialEntrepreneur(map.getString("serialEntrepreneur"));
        form.setHeaderGender(map.getString("headerGender"));
        form.setTaxKind(map.getString("taxKind"));
        form.setHeader(map.getString("header"));
        form.setStatisticHeader(map.getString("statisticHeader"));
        form.setSubmitHeader(map.getString("submitHeader"));
        form.setSubmitPhone(map.getString("submitPhone"));
        form.setSubmitTime(map.getString("submitTime"));
        form.setRiskInvestment(map.getString("riskInvestment"));
        form.setArea(map.getString("area"));
        form.setInstitutions(map.getString("institutions"));
        form.setTotalTransformation(map.getString("totalTransformation"));
        form.setRelying(map.getString("relying"));
        form.setWinning(map.getString("winning"));
        form.setResult(map.getString("result"));
        form.setIncubateIncome(map.getString("incubateIncome"));
        form.setIncubateProduct(map.getString("incubateProduct"));
        form.setIncubateProfit(map.getString("incubateProfit"));
        form.setIncubateTax(map.getString("incubateTax"));
        form.setIncubateOut(map.getString("incubateOut"));
        form.setEmployee(map.getString("employee"));
        form.setDoctor(map.getString("doctor"));
        form.setMaster(map.getString("master"));
        form.setGraduate(map.getString("graduate"));
        form.setBachelor(map.getString("bachelor"));
        form.setCollege(map.getString("college"));
        form.setTecSecondary(map.getString("tecSecondary"));
        form.setTecActivists(map.getString("tecActivists"));
        form.setRadNumber(map.getString("radNumber"));
        form.setReturnees(map.getString("returnees"));
        form.setTalents(map.getString("talents"));
        form.setTrainee(map.getString("trainee"));
        form.setEmployment(map.getString("employment"));
        form.setApplications(map.getString("applications"));
        form.setApplicationsPatent(map.getString("applicationsPatent"));
        form.setGranted(map.getString("granted"));
        form.setGrantedPatent(map.getString("grantedPatent"));
        form.setValid(map.getString("valid"));
        form.setValidPatent(map.getString("validPatent"));
        form.setSoftCopyright(map.getString("softCopyright"));
        form.setPlantVariety(map.getString("plantVariety"));
        form.setIcLayout(map.getString("icLayout"));
        form.setForeignPatents(map.getString("foreignPatents"));
        form.setContractTransaction(map.getString("contractTransaction"));
        form.setContractUrnover(map.getString("contractUrnover"));
        form.setProjectNum(map.getString("projectNum"));
        form.setTotalAwards(map.getString("totalAwards"));
        form.setProvinceAwards(map.getString("provinceAwards"));
        form.setUnderProjects(map.getString("underProjects"));
        form.setNationalProject(map.getString("nationalProject"));
        form.setSchoolProject(map.getString("schoolProject"));
        form.setDeclarationName(map.getString("declarationName"));
        form.setDeclarationNum(map.getString("declarationNum"));
        form.setExpenditure(map.getString("expenditure"));
        form.setRadExpenditure(map.getString("radExpenditure"));
        form.setProductExpenditure(map.getString("productExpenditure"));
        form.setGovernmentGrant(map.getString("governmentGrant"));
        form.setSelfRaised(map.getString("selfRaised"));
        return form;
    }
}