package com.qks.makerSpace.entity.response;

import lombok.Data;

@Data
public class TimeFormRes {
    private String formId;
    private String time;
    private String teamName;
    private String creditCode;
    private String getTime;
    private String adminAudit;
    private String leaderAudit;
}
