package com.qks.makerSpace.entity.database;

import lombok.Data;

/**
 * @author 15998
 */
@Data
public class Demand {
    private String id;
    private String creditCode;
    private String leaseArea;
    private String position;
    private String lease;
    private String floor;
    private String electric;
    private String water;
    private String web;
    private String others;
    private String time;
}
