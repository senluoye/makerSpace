package com.qks.makerSpace.entity.database;

import lombok.*;

@Data
public class User {
    private String userId;
    private String name;
    private String password;
    private String userDescribe;
}
