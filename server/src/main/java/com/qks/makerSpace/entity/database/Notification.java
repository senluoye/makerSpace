package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Notification {
    private String noticeId;
    private String text;
    private String forTop;
    private String noticeTime;
}