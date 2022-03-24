package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class BriefFormReq {
    private String id;
    private String time;
    private String teamName;
    private String creditCode;
    private String getTime;
    private String adminAudit;
    private String leaderAudit;
    private int alive;
}