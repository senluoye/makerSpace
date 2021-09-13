package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity {

    private String activityId;
    private Integer underProjects;
    private Integer nationalProject;
    private Integer expenditure;
    private Integer radExpenditure;
    private Integer productExpenditure;
    private Integer govermentGrant;
    private Integer selfRaised;

}
