package com.qks.makerSpace.entity.response;

import lombok.Data;

/**
 * @description 缴费表
 * @author 15998
 */
@Data
public class ContractRes {
    private String contractId;
    // 公司名称
    private String name;
    // 缴费凭证（文件）
    private String voucher;
    // 提交时间
    private String submitTime;
    // 提交金额
    private Integer amount;
    // 季度
    private Integer quarter;
    // 年度
    private Integer year;
    // 费用类型
    private String describe;
}
