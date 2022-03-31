package com.qks.makerSpace.entity.database;

import lombok.*;

@Data
public class User {
    private String userId;
    private String name;
    private String password;
    // 0表示领导，11表示科技园管理员，12表示众创空间管理员
    // 2表示科技园new，3表示科技园old，4表示众创空间
    private Integer userDescribe;
    private String email;
    private String submitTime; // 表示分配用户账号的时间
    private Boolean alive; // 退出状态
}
