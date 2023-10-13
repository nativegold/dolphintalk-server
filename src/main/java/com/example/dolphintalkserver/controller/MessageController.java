package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.message.*;
import com.example.dolphintalkserver.service.ChatService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MessageController {    // 채팅방 웹소켓 컨트롤러
    private final ChatService chatService;
    private final SimpMessageSendingOperations sendingOperations;

    // 사용자 채팅방 입장
    @MessageMapping("/chat/join")
    public void join(@RequestBody JoinMessageRequestDTO request, SimpMessageHeaderAccessor headerAccessor) {
        String senderIp = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("ip").toString();
        JoinMessageResponseDTO response = chatService.sendJoinMessage(request, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + request.getRoomId(), response);
    }

    // 사용자 채팅방 퇴장
    @MessageMapping("/chat/leave")
    public void leave(@RequestBody LeaveMessageRequestDTO request, SimpMessageHeaderAccessor headerAccessor) {
        String senderIp = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("ip").toString();
        LeaveMessageResponseDTO response = chatService.sendLeaveMessage(request, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + response.getRoomId(), response);
    }

    // 사용자 텍스트 메시지 전송
    @MessageMapping("/chat/text")
    public void sendText(@RequestBody TextMessageRequestDTO request, SimpMessageHeaderAccessor headerAccessor) {
        String senderIp = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("ip").toString();
        TextMessageResponseDTO response = chatService.sendText(request, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + response.getRoomId(), response);
    }

    // 사용자 이미지 전송
    @MessageMapping("/chat/image")
    public void sendImage(@RequestBody ImageMessageRequestDTO request, SimpMessageHeaderAccessor headerAccessor) {
        String senderIp = Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("ip").toString();
        ImageMessageResponseDTO response = chatService.sendImage(request, senderIp);

        sendingOperations.convertAndSend("/topic/chat/room/" + response.getRoomId(), response);
    }
}
