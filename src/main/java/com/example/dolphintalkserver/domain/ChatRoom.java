package com.example.dolphintalkserver.domain;

import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class ChatRoom {     // 채팅방 도메인
    // 채팅방 ID
    private final String roomId;
    // 채팅방 이름
    private final String roomName;
    private final AtomicInteger userCount = new AtomicInteger(0);

    public ChatRoom(String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
    }

    public void increaseUserCount() {
        userCount.incrementAndGet();
    }

    public void decreaseUserCount() {
        userCount.decrementAndGet();
    }

    public int getUserCount() {
        return userCount.get();
    }
}
