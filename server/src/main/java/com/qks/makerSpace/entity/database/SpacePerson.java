package com.qks.makerSpace.entity.database;

import lombok.AllArgsConstructor;
import lombok.Data;

//众创空间申请人员表，两类企业共用字段
@Data
public class SpacePerson {
    private String spacePersonId;
    private String inApplyId;
    private String personName;
    private String department;
    private String major;
    private String personPhone;
    private String personQq;
    private String personWechat;
    private String note;
    private String submitTime; // 表单提交时间
}