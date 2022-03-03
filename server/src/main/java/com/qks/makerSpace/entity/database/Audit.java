package com.qks.makerSpace.entity.database;

import lombok.Data;

/**
 * 入园申请审核表
 */
@Data
public class Audit {
    private String auditId; // UUID
    private String creditCode;
    private String administratorAudit; // 管理员同意情况
    private String leadershipAudit; // 领导同意情况
    private String describe; // 科技园/众创空间
    private String submitTime; // 申请提交的时间
}
