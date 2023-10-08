package com.example.dolphintalkserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDTO {
    public enum MessageType {
        TALK,
        IMAGE
    }

    private String sender;
    private String message;
    private String ip;
}
