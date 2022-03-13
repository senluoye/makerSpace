package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class AdminSpaceApplyingReq {
    String inApplyId;
    String name;
    String submitTime;
    String administratorAudit;
    String describe;
}
