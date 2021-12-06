package com.qks.makerSpace.entity;

import lombok.Data;


//众创空间申请人员表，两类企业共用字段
@Data
public class SpacePerson {
    private String personName;
    private String department;
    private String major;
    private String personPhone;
    private String personQq;
    private String personWechat;
    private String note;
}