package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class FormAwards {
    private String formAwardsId; // 对应Form表
    private String awardsId; // 真正的主键
    private String awardsFile;
}
