package com.qks.makerSpace.entity.response;

import lombok.Data;

@Data
public class DemandRes {
    private String id;
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
