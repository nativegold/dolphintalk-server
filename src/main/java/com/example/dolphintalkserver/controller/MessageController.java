package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.MessageRequestDTO;
import com.example.dolphintalkserver.dto.MessageResponseDTO;
import com.example.dolphintalkserver.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MessageController {    // 메시지 전송 관련 컨트롤러
    private final ChatService chatService;
    private final SimpMessageSendingOperations sendingOperations;

    // 해당 채팅방 메시지 전송
    @MessageMapping("/chat/message")
    public void sendMessage(@RequestBody MessageRequestDTO messageRequestDTO, SimpMessageHeaderAccessor headerAccessor) {
        String senderIp = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("ip").toString();
        MessageResponseDTO response = chatService.sendMessage(messageRequestDTO, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + response.getRoomId(), response);
    }
}
