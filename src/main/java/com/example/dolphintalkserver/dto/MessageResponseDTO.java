package com.example.dolphintalkserver.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class MessageResponseDTO {
    private final String roomId;
    private final String sender;
    private final String message;
    private final String senderIp;

    @Builder
    public MessageResponseDTO(String roomId, String sender, String message, String senderIp) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.senderIp = senderIp;
    }
}
