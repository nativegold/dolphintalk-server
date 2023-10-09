package com.example.dolphintalkserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MessageRequestDTO {
    public enum MessageType {
        TALK,
        IMAGE
    }

    private String roomId;
    private String sender;
    private String message;
}
