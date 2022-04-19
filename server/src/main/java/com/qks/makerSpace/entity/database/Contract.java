package com.qks.makerSpace.entity.database;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 缴费表
 * @author 15998
 */
@Data
public class Contract {
    private String contractId;
    // 公司代码
    private String creditCode;
    // 缴费凭证（文件）
    private String voucher;
    // 提交时间
    private String submitTime;
    // 提交金额
    private String amount;
    // 年度
    private Integer year;
    // 季度
    private Integer quarter;
    // 费用类型
    private String describe;
}
