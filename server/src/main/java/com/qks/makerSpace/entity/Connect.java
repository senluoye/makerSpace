package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Connect {

    @NonNull
    private String id;

    private String enterpriseId;
    private String employeeId;
    private String propertyId;
    private String activityId;
    private String teamName;
}
