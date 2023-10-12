package com.example.dolphintalkserver.dto.message;

import com.example.dolphintalkserver.dto.type.MessageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LeaveMessageResponseDTO {
    private String roomId;
    private String sender;
    private String senderIp;
    private final String type = MessageType.LEAVE;

    @Builder
    public LeaveMessageResponseDTO(String roomId, String sender, String senderIp) {
        this.roomId = roomId;
        this.sender = sender;
        this.senderIp = senderIp;
    }
}
