package com.example.dolphintalkserver.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomsRequestDTO {     // 채팅방 리스트 요청 DTO
    private String nickname;
}
