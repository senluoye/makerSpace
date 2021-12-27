package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class NewDemand {
    private String id;
    private String newDemandId;
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