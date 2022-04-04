package com.qks.makerSpace.entity.response;

import lombok.Data;

@Data
public class UserAccountApplyingRes {
    private String userAccountId;
    private String name; // 公司名称
    private int describe; // 公司描述
    private String email; // 公司邮箱
    private String submitTime; // 申请提交时间
    private Boolean administratorAudit; // 领导审核状态
}
