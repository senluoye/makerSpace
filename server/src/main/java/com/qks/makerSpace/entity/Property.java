package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Property {

    @NonNull
    private String propertyId;

    private String enterpriseId;
    private String teamName;

    private Integer applications;
    private Integer applicationsPatent;
    private Integer patentNum;
    private Integer softNum;
    private Integer trademarkNum;
    private Integer granted;
    private Integer grantedPatent;
    private Integer valid;
    private Integer validPatent;
    private Integer softCopyright;
    private Integer plantVariety;
    private Integer icLayout;
    private Integer foreignPatents;
    private Integer contractTransaction;
    private Integer contractTurnover;
    private Integer projectNum;
    private Integer awards;

}
