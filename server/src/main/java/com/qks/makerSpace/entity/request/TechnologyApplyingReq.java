package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class TechnologyApplyingReq {
    String creditCode;
    String name;
    String submitTime;
    String administratorAudit;
    String describe;
}
