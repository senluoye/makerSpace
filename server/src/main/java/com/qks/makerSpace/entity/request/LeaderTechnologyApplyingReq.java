package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class LeaderTechnologyApplyingReq {
    String id;
    String creditCode;
    String name;
    String submitTime;
    String administratorAudit;
    String leadershipAudit;
    String describe;
}
