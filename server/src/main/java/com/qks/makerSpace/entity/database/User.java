package com.qks.makerSpace.entity.database;

import lombok.*;

@Data
public class User {
    private String userId;
    private String name;
    private String password;
    private Integer userDescribe; // 0表示领导，1表示管理员，2表示科技园new，3表示科技园old，4表示众创空间
    private String email;
}
