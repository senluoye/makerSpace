package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Audit {

    private String auditId;
    private boolean administratorAudit;
    private boolean leadershipAudit;
    private String describe;

}