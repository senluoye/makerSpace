package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Audit {

    private String auditId;
    private String administratorAudit;
    private String leadershipAudit;
    private String describe;

}
