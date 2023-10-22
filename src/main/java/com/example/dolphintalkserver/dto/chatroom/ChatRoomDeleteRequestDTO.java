package com.example.dolphintalkserver.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomDeleteRequestDTO {     // 채팅방 삭제 요청 DTO
    private String roomId;
}
