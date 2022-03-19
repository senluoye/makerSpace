package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class UserApplyingAudit {
    private String userApplyingAuditId;
    private String administratorAudit; // 管理员审核情况
    private String agreeTime; // 同意时间
}
