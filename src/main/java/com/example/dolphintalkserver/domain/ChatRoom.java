package com.example.dolphintalkserver.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {     // 채팅방 도메인
    // 채팅방 ID
    private final String roomId;
    // 채팅방 이름
    private final String roomName;

    public ChatRoom(String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
    }
}
