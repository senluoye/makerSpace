package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class News {
    private String NewId;
    private String creditCode;
    private String organizationCode;
    private String password;
    private String name;
    private byte[] picture;
    private String represent;
    private String representCard;
    private String representPhone;
    private String representEmail;
    private String agent;
    private String agentPhone;
    private String agentEmail;
    private String registerCapital;
    private String realCapital;
    private String originNumber;
    private String registerTime;
    private String nature;
    private byte[] certificate;
    private String involved;
    private String mainBusiness;
    private String business;
    private String newDemandId;
    private String newShareholderId;
    private String newMainpersonId;
    private String newProjectId;
    private String newIntellectualId;
    private String suggestion;
    private String note;
    private String submitTime;
    private String room;
    private String inApplyId;
    private String outApplyId;
}