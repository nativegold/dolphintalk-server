package com.example.dolphintalkserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final ChatRoomService chatRoomService;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String roomId = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("roomId").toString();

        chatRoomService.addUserToRoom(roomId);
    }

    // WebSocket 연결 해제 시
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String roomId = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("roomId").toString();

        chatRoomService.removeUserFromRoom(roomId);
    }
}
