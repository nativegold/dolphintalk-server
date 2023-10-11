package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.dto.MessageRequestDTO;
import com.example.dolphintalkserver.dto.MessageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    // 응답 메시지 생성
    public MessageResponseDTO sendMessage(MessageRequestDTO request, String senderIp) {

        return MessageResponseDTO.builder()
                .sender(request.getSender())
                .message(request.getMessage())
                .senderIp(senderIp)
                .build();
    }

}
