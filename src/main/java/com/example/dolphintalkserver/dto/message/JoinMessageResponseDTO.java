package com.example.dolphintalkserver.dto.message;

import com.example.dolphintalkserver.dto.type.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinMessageResponseDTO {
    private String roomId;
    private String sender;
    private String senderIp;
    private final String type = MessageType.JOIN;

    @Builder
    public JoinMessageResponseDTO(String roomId, String sender, String senderIp) {
        this.roomId = roomId;
        this.sender = sender;
        this.senderIp = senderIp;
    }
}
