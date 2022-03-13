package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class LeaderSpaceApplyingReq {
    String inApplyId;
    String name;
    String submitTime;
    String leadershipAudit;
    String describe;
}
