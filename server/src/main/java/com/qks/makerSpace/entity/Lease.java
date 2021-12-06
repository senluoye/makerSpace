package com.qks.makerSpace.entity;

import lombok.Data;

/**
 * 已经入驻科技园和众创空间的表
 */
@Data
public class Lease {
   private int leaseId;
   private String name;
   private String room;
   private String describe;
}
