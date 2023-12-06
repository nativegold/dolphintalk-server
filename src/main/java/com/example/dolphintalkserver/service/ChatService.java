package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.dto.message.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ChatService {
    public JoinMessageResponseDTO sendJoinMessage(JoinMessageRequestDTO request, String senderIp) {
        return JoinMessageResponseDTO.builder()
                .roomId(request.getRoomId())
                .sender(request.getSender())
                .senderIp(maskIp(senderIp))
                .build();
    }

    public LeaveMessageResponseDTO sendLeaveMessage(LeaveMessageRequestDTO request, String senderIp) {
        return LeaveMessageResponseDTO.builder()
                .roomId(request.getRoomId())
                .sender(request.getSender())
                .senderIp(maskIp(senderIp))
                .build();
    }

    public TextMessageResponseDTO sendText(TextMessageRequestDTO request, String senderIp) {
        // 응답 메시지 생성
        return TextMessageResponseDTO.builder()
                .roomId(request.getRoomId())
                .sender(request.getSender())
                .content(request.getContent())
                .senderIp(maskIp(senderIp))
                .build();
    }

    public ImageMessageResponseDTO sendImage(ImageMessageRequestDTO request, String senderIp) {
        return ImageMessageResponseDTO.builder()
                .roomId(request.getRoomId())
                .sender(request.getSender())
                .imageUrl(request.getImageUrl())
                .senderIp(maskIp(senderIp))
                .build();
    }

    private String maskIp(String senderIp) {
        String ipAddress = senderIp.split(":")[0].substring(1); // IP 주소만 추출
        String[] octets = ipAddress.split("\\.");

        // 마지막 옥텟을 제외하고 출력
        return String.join(".", octets[0], octets[1], octets[2]) + ".*";
    }
}
