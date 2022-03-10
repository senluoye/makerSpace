package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class SpaceApplyingReq {
    String inApplyId;
    String name;
    String submitTime;
    String administratorAudit;
    String describe;
}
