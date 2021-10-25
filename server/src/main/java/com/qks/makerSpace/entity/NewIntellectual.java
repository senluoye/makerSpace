package com.qks.makerSpace.entity;

import lombok.Data;

@Data
public class NewIntellectual {
    private String id;
    private String name;
    private String kind;
    private String applyTime;
    private String approvalTime;
    private byte[] intellectualFile;
}