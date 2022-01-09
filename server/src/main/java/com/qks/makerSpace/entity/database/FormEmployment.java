package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class FormEmployment {
    private String formEmploymentId; // 这里对应Form表
    private String employmentId; // 注意，这才是主键
    private byte[] contractFile;
}
