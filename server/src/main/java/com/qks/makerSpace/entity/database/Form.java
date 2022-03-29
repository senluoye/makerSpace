package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Form {
    private String  formId;
    private String  year;
    private String  quarter;
    private String	teamName;
    private String	creditCode;
    private String	registerTime;
    private String	joinTime;
    private String	registerCapital;
    private String	registerKind;
    private String	industryKind;
    private String	field;
    private String	graduatedEnterprise;
    private String	graduatedTime;
    private String	highEnterprise;
    private String	highEnterpriseId;
    private String	mediumSized;
    private byte[]	mediumFile;
    private String	mentorRelationship;
    private String	headerKind;
    private byte[]	headerFile;
    private String	serialEntrepreneur;
    private String	headerGender;
    private String	taxKind;
    private String	header;
    private String	statisticHeader;
    private String	submitHeader;
    private String	submitPhone;
    private String	submitTime;
    private String	riskInvestment;
    private String	area;
    private String	institutions;
    private String	totalTransformation;
    private String	relying;
    private String	winning;
    private String	result;
    private String	incubateIncome;
    private String	incubateProduct;
    private String	incubateProfit;
    private String	incubateTax;
    private String	incubateOut;
    private String	employee;
    private String	doctor;
    private String	master;
    private String	graduate;
    private String	bachelor;
    private String	college;
    private String	tecSecondary;
    private String	tecActivists;
    private String	radNumber;
    private String	returnees;
    private String	talents;
    private String	trainee;
    private String	employment;
    private String	employmentId;
    private String	applications;
    private String	applicationsPatent;
    private String	granted;
    private String	grantedPatent;
    private String	valid;
    private String	validPatent;
    private String	softCopyright;
    private String	plantVariety;
    private String	icLayout;
    private String	foreignPatents;
    private String	contractTransaction;
    private String	contractUrnover;
    private String	projectNum;
    private String	totalAwards;
    private String	awardsId;
    private String	provinceAwards;
    private String	underProjects;
    private String	nationalProject;
    private String	schoolProject;
    private String	declarationName;
    private String	declarationNum;
    private String	expenditure;
    private String	radExpenditure;
    private String	productExpenditure;
    private String	governmentGrant;
    private String	selfRaised;
    private String  getTime; // 这个getTime用于区分表单提交时间，和 highEnterpriseData里面那个getTime不一样
    private String  alive;
    private String  adminAudit;
    private String  leaderAudit;
}