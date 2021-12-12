package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class NewProject {
    private String id;
    private String newProjectId;
    private String projectBrief;
    private String advantage;
    private String market;
    private String energy;
    private String pollution;
    private String noise;
    private String others;
}