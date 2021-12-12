package com.qks.makerSpace.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

//众创空间申请人员表，两类企业共用字段
@Data
@AllArgsConstructor
public class SpacePerson {
    private String inApplyId;
    private String personName;
    private String department;
    private String major;
    private String personPhone;
    private String personQq;
    private String personWechat;
    private String note;
}