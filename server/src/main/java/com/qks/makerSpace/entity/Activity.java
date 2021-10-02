package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Activity {

    @NonNull
    private String activityId;

    private Integer underProjects;
    private Integer nationalProject;
    private Integer expenditure;
    private Integer radExpenditure;
    private Integer productExpenditure;
    private Integer governmentGrant;
    private Integer selfRaised;

}
