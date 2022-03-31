package com.qks.makerSpace.entity.response;

import lombok.Data;

@Data
public class AdminSuggestion {
    private String id; // 表的id
    private String creditCode;
    private String suggestion;
    private String note;
}