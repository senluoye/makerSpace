package com.qks.makerSpace.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 众创空间申请，此为所有企业公用模板
 */
@Data
@AllArgsConstructor
public class Space {
    private String InApplyId;
    private String describe; // 值表示新/旧企业
    private String createName;
    private String applyTime;
    private String teamNumber;
    private String brief;
    private String help;

    /**
     * 注意，类中缺少承诺人、日期、办公室主任意见、领导审批意见字段
     */
}