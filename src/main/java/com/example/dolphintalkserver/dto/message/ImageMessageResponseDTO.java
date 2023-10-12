package com.example.dolphintalkserver.dto.message;

import com.example.dolphintalkserver.dto.type.MessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageMessageResponseDTO {
    private final String roomId;
    private final String sender;
    private final String senderIp;
    private final String type = MessageType.IMAGE;
    private final String imageUrl;

    @Builder
    public ImageMessageResponseDTO(String roomId, String sender, String imageUrl, String senderIp) {
        this.roomId = roomId;
        this.sender = sender;
        this.imageUrl = imageUrl;
        this.senderIp = senderIp;
    }
}
