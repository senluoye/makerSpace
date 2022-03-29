package com.qks.makerSpace.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qks.makerSpace.entity.Temp.HighEnterpriseData;
import com.qks.makerSpace.entity.database.*;
import com.qks.makerSpace.entity.request.FormReq;

import java.util.*;

public class FormParserUtils {
    public static FormReq parser(JSONObject map) {
        FormReq form  = new FormReq();

        form.setYear(map.getString("time").split("-")[0]);
        form.setQuarter(map.getString("time").split("-")[1]);
        form.setTeamName(map.getString("teamName"));
        form.setCreditCode(map.getString("creditCode"));
        form.setRegisterTime(map.getString("registerTime"));
        form.setJoinTime(map.getString("joinTime"));
        form.setRegisterCapital(map.getString("registerCapital"));
        form.setRegisterKind(map.getString("registerKind"));
        form.setIndustryKind(map.getString("industryKind"));
        form.setField(map.getString("field"));
        form.setGraduatedEnterprise(map.getString("graduatedEnterprise"));
        form.setGraduatedTime(map.getString("graduatedTime"));
        form.setHighEnterprise(map.getString("highEnterprise"));
        form.setMediumSized(map.getString("mediumSized"));
        form.setMentorRelationship(map.getString("mentorRelationship"));
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

    public static Map<String, Object> FormMapParser(HighEnterpriseData highEnterpriseData, Form form) {
        Map<String, Object> data = new HashMap<>();

        data.put("year", form.getYear());
        data.put("quarter", form.getQuarter());
        data.put("teamName", form.getTeamName());
        data.put("creditCode", form.getCreditCode());
        data.put("registerTime", form.getRegisterTime());
        data.put("joinTime", form.getJoinTime());
        data.put("registerCapital", form.getRegisterCapital());
        data.put("registerKind", form.getRegisterKind());
        data.put("industryKind", form.getIndustryKind());
        data.put("field", form.getField());
        data.put("graduatedEnterprise", form.getGraduatedEnterprise());
        data.put("graduatedTime", form.getGraduatedTime());
        data.put("highEnterprise", form.getHighEnterprise());
        data.put("highEnterpriseData", JSON.toJSONString(highEnterpriseData));
        data.put("mediumSized", form.getMediumSized());
        data.put("mentorRelationship", form.getMentorRelationship());
        data.put("headerKind", form.getHeaderKind());
        data.put("serialEntrepreneur", form.getSerialEntrepreneur());
        data.put("headerGender", form.getHeaderGender());
        data.put("taxKind", form.getTaxKind());
        data.put("header", form.getHeader());
        data.put("statisticHeader", form.getStatisticHeader());
        data.put("submitHeader", form.getSubmitHeader());
        data.put("submitPhone", form.getSubmitPhone());
        data.put("submitTime", form.getSubmitTime());
        data.put("riskInvestment", form.getRiskInvestment());
        data.put("area", form.getArea());
        data.put("institutions", form.getInstitutions());
        data.put("totalTransformation", form.getTotalTransformation());
        data.put("relying", form.getRelying());
        data.put("winning", form.getWinning());
        data.put("result", form.getResult());
        data.put("incubateIncome", form.getIncubateIncome());
        data.put("incubateProduct", form.getIncubateProduct());
        data.put("incubateProfit", form.getIncubateProfit());
        data.put("incubateTax", form.getIncubateTax());
        data.put("incubateOut", form.getIncubateOut());
        data.put("employee", form.getEmployee());
        data.put("doctor", form.getDoctor());
        data.put("master", form.getMaster());
        data.put("graduate", form.getGraduate());
        data.put("bachelor", form.getBachelor());
        data.put("college", form.getCollege());
        data.put("tecSecondary", form.getTecSecondary());
        data.put("tecActivists", form.getTecActivists());
        data.put("radNumber", form.getRadNumber());
        data.put("returnees", form.getReturnees());
        data.put("talents", form.getTalents());
        data.put("trainee", form.getTrainee());
        data.put("employment", form.getEmployment());
        data.put("applications", form.getApplications());
        data.put("applicationsPatent", form.getApplicationsPatent());
        data.put("granted", form.getGranted());
        data.put("grantedPatent", form.getGrantedPatent());
        data.put("valid", form.getValid());
        data.put("validPatent", form.getValidPatent());
        data.put("softCopyright", form.getSoftCopyright());
        data.put("plantVariety", form.getPlantVariety());
        data.put("icLayout", form.getIcLayout());
        data.put("foreignPatents", form.getForeignPatents());
        data.put("contractTransaction", form.getContractTransaction());
        data.put("contractUrnover", form.getContractUrnover());
        data.put("projectNum", form.getProjectNum());
        data.put("totalAwards", form.getTotalAwards());
        data.put("provinceAwards", form.getProvinceAwards());
        data.put("underProjects", form.getUnderProjects());
        data.put("nationalProject", form.getNationalProject());
        data.put("schoolProject", form.getSchoolProject());
        data.put("declarationName", form.getDeclarationName());
        data.put("declarationNum", form.getDeclarationNum());
        data.put("expenditure", form.getExpenditure());
        data.put("radExpenditure", form.getRadExpenditure());
        data.put("productExpenditure", form.getProductExpenditure());
        data.put("governmentGrant", form.getGovernmentGrant());
        data.put("selfRaised", form.getSelfRaised());

        return data;
    }

    public static Map<String, Object> FormMapParser(Old old) {
        Map<String, Object> data = new HashMap<>();




        return data;
    }
}