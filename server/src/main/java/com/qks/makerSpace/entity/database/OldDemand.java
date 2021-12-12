package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class OldDemand {
    private String id;
    private String oldDemandId;
    private String lease_area;
    private String position;
    private String lease;
    private String floor;
    private String electric;
    private String water;
    private String web;
    private String others;
}
