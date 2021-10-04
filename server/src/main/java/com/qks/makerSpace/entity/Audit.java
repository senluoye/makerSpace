package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Audit {

    @NonNull
    private String auditId;

    private String enterpriseId;
    private String submissionTime;
    private String auditTime;
    private Boolean isAudit;
}
