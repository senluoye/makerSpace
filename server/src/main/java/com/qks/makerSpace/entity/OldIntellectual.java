package com.qks.makerSpace.entity;

import lombok.Data;

@Data
public class OldIntellectual {
    private String id;
    private String name;
    private String kind;
    private String apply_time;
    private String approval_time;
    private byte[] intellectual_file;
}
