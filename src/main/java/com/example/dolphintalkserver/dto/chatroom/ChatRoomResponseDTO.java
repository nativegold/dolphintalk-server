package com.example.dolphintalkserver.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatRoomResponseDTO {      // 채팅방 정보 응답 DTO
    String roomId;
    String roomName;
}
