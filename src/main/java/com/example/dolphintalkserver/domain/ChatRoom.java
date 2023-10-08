package com.example.dolphintalkserver.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {
    private final String roomId;
    private final String roomName;

    public ChatRoom(String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
    }
}
