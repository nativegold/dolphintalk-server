package com.example.dolphintalkserver.dto;

import com.example.dolphintalkserver.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ChatRoomListResponseDTO {
    List<ChatRoom> chatRooms;
}
