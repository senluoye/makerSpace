package com.qks.makerSpace.entity.database;

import lombok.Data;

@Data
public class Message {
    private String messageId;
    private String userId;
    private String messageText;
    private String messageTime;
}
