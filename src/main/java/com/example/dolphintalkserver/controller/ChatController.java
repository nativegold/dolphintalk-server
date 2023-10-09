package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.MessageRequestDTO;
import com.example.dolphintalkserver.dto.MessageResponseDTO;
import com.example.dolphintalkserver.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void message(HttpServletRequest httpServletRequest,
                        MessageRequestDTO messageRequestDTO) {
        String senderIp = httpServletRequest.getRemoteAddr();
        MessageResponseDTO response = chatService.sendMessage(messageRequestDTO, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + response.getRoomId(), response);
    }
}
