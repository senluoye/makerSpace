package com.qks.makerSpace.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 15998
 */
@Data
@Builder
public class DemandRes {
    private String id;
    private String name;
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
