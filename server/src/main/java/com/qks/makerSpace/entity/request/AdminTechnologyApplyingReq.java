package com.qks.makerSpace.entity.request;

import lombok.Data;

@Data
public class AdminTechnologyApplyingReq {
    String creditCode;
    String name;
    String submitTime;
    String administratorAudit;
    String describe;
}
