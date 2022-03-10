package com.qks.makerSpace.entity.database;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 众创空间申请，此为所有企业公用模板
 */
@Data
public class Space {
    private String spaceId;
    private String inApplyId; // 表中有，则拿表中的填，没有，则随机生成UUID
    private String createName;
    private String applyTime;
    private String teamNumber;
    private String describe; // 项目概括
    private String help;
    private String accepter;
    private String time;
    private String officeOpinion;
    private String leaderOpinion;
    private String submitTime; // 表单提交时间
}