package com.qks.makerSpace.entity.response;

import lombok.Data;

/**
 * 获取以往所有入园申请记录的返回体
 */
@Data
public class TechnologyApplyingRes {
    private String id;
    private String name;
    private String submitTime;
    private String administratorAudit;
    private String leadershipAudit;
    private String suggestion;
    private String note;
}
