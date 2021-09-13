package com.qks.makerSpace.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User {
    @NonNull
    private String name;

    @NonNull
    private String password;

}
