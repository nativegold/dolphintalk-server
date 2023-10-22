package com.example.dolphintalkserver.dto.chatroom;

import com.example.dolphintalkserver.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatRoomsResponseDTO {     // 채팅방 리스트 응답 DTO
    List<ChatRoom> chatRooms;
}
