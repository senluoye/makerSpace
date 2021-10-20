package com.qks.makerSpace.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class User {
    @NonNull
    private String name;

    @NonNull
    private String password;

}
