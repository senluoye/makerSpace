package com.qks.makerSpace.entity.response;

import com.qks.makerSpace.entity.database.SpacePerson;
import lombok.Data;

import java.util.List;

@Data
public class AllSpace {
    private String inApplyId;
    private String createName;
    private String applyTime;
    private String teamNumber;
    List<SpacePerson> Person;
    private String brief;
    private String help;
    private boolean administratorAudit;
}
