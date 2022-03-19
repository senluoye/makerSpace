package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class EmailAuth {
    private String emailAuthId;
    private String userId;
    private String authorizationCode;
}
