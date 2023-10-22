package com.example.dolphintalkserver.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomCreateRequestDTO {     // 채팅방 생성 요청 DTO
    private String roomName;
}
