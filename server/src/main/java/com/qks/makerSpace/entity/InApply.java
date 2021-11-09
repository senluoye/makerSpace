package com.qks.makerSpace.entity;

import lombok.Data;


//众创空间申请，两类企业共用字段
@Data
public class InApply {
    private String id;
    private String newInApplyId;
    private String createName;
    private String applyTime;
    private String teamNumber;
    private String newPersonId;
    private String brief;
    private String help;
}