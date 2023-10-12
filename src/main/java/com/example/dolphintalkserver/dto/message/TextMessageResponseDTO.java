package com.example.dolphintalkserver.dto.message;

import com.example.dolphintalkserver.dto.type.MessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TextMessageResponseDTO {
    private final String roomId;
    private final String sender;
    private final String senderIp;
    private final String type = MessageType.TEXT;
    private final String content;

    @Builder
    public TextMessageResponseDTO(String roomId, String sender, String content, String senderIp) {
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
        this.senderIp = senderIp;
    }
}
