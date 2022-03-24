package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Contract {
    private String contractId;
    private String creditCode;
    private byte[] voucher;
    private String submitTime;
}
