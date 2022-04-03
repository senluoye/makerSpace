package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class OldIntellectual {
    private String id;
    private String oldIntellectualId;
    private String name;
    private String kind;
    private String applyTime;
    private String approvalTime;
    private String intellectualFile; // 文件路径
}
