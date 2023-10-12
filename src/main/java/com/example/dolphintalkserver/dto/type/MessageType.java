package com.example.dolphintalkserver.dto.type;

import org.springframework.context.annotation.Configuration;

// 채팅 메시지 타입
public class MessageType {
    public static final String JOIN = "JOIN";   // 입장
    public static final String LEAVE = "LEAVE";     // 퇴장
    public static final String TEXT = "TEXT";   // 텍스트
    public static final String IMAGE = "IMAGE";     // 이미지
}
