package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Audit {
    private String auditId; // 科技园对应的是creditCode， 众创空间对应的是InApplyId
    private String administratorAudit;
    private String leadershipAudit;
    private String describe;

}
