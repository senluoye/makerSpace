package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class UserAccountApplying {
    private String userAccountId;
    private String name; // 公司名称
    private String password; // 公司密码
    private int describe; // 公司描述
    private String email; // 公司邮箱
    private Boolean administratorAudit; // 领导审核情况
    private String submitTime; // 申请提交时间
}
