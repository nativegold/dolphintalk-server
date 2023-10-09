package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.dto.MessageRequestDTO;
import com.example.dolphintalkserver.dto.MessageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    public MessageResponseDTO sendMessage(MessageRequestDTO request, String senderIp) {

        return MessageResponseDTO.builder()
                .sender(request.getSender())
                .message(request.getMessage())
                .senderIp(senderIp)
                .build();
    }
}
