package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.message.*;
import com.example.dolphintalkserver.service.ChatService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @Mock
    ChatService chatService;

    @Mock
    SimpMessageHeaderAccessor headerAccessor;

    @Mock
    SimpMessageSendingOperations sendingOperations;

    @InjectMocks
    MessageController messageController;


    @DisplayName("join: 채팅방 입장 메시지 전송에 성공한다.")
    @Test
    public void testJoin() {
        // 준비 (Given)
        final String testRoomId = "testRoomId";     // 테스트 채팅방 ID
        final String testSender = "testSender";     // 테스트 작성자 닉네임
        final String testSenderIp = "127.0.0.1";    // 테스트 아이피
        JoinMessageRequestDTO testRequest = new JoinMessageRequestDTO(testRoomId, testSender);  // 테스트 requestDTO

        JoinMessageResponseDTO expectedResponse = JoinMessageResponseDTO.builder()      // 예상 결과 값
                .roomId(testRoomId)
                .sender(testSender)
                .senderIp("127.0.0.*")
                .build();

        when(chatService.sendJoinMessage(testRequest, testSenderIp)).thenReturn(expectedResponse);
        when(headerAccessor.getSessionAttributes()).thenReturn(Map.of("ip", testSenderIp));

        // 실행 (When)
        messageController.join(testRequest, headerAccessor);

        // 검증 (Then)
        verify(chatService).sendJoinMessage(eq(testRequest), eq(testSenderIp));
        verify(sendingOperations).convertAndSend(eq("/topic/chat/room/" + testRoomId), eq(expectedResponse));
    }

    @DisplayName("leave: 채팅방 퇴장 메시지 전송에 성공한다.")
    @Test
    void leave() {
        // 준비 (Given)
        final String testRoomId = "testRoomId";     // 테스트 채팅방 ID
        final String testSender = "testSender";     // 테스트 작성자 닉네임
        final String testSenderIp = "127.0.0.1";    // 테스트 아이피
        LeaveMessageRequestDTO testRequest = new LeaveMessageRequestDTO(testRoomId, testSender);  // 테스트 requestDTO

        LeaveMessageResponseDTO expectedResponse = LeaveMessageResponseDTO.builder()      // 예상 결과 값
                .roomId(testRoomId)
                .sender(testSender)
                .senderIp("127.0.0.*")
                .build();

        when(chatService.sendLeaveMessage(testRequest, testSenderIp)).thenReturn(expectedResponse);
        when(headerAccessor.getSessionAttributes()).thenReturn(Map.of("ip", testSenderIp));

        // 실행 (When)
        messageController.leave(testRequest, headerAccessor);

        // 검증 (Then)
        verify(chatService).sendLeaveMessage(eq(testRequest), eq(testSenderIp));
        verify(sendingOperations).convertAndSend(eq("/topic/chat/room/" + testRoomId), eq(expectedResponse));
    }

    @DisplayName("sendText: 텍스트 메시지 전송에 성공한다.")
    @Test
    void sendText() {
        // 준비 (Given)
        final String testRoomId = "testRoomId";     // 테스트 채팅방 ID
        final String testSender = "testSender";     // 테스트 작성자 닉네임
        final String testContent = "테스트 메시지";    // 테스트 아이피
        final String testSenderIp = "127.0.0.1";
        TextMessageRequestDTO testRequest = new TextMessageRequestDTO(testRoomId, testSender, testContent);  // 테스트 requestDTO

        TextMessageResponseDTO expectedResponse = TextMessageResponseDTO.builder()      // 예상 결과 값
                .roomId(testRoomId)
                .sender(testSender)
                .senderIp("127.0.0.*")
                .content(testContent)
                .build();

        when(chatService.sendText(testRequest, testSenderIp)).thenReturn(expectedResponse);
        when(headerAccessor.getSessionAttributes()).thenReturn(Map.of("ip", testSenderIp));

        // 실행 (When)
        messageController.sendText(testRequest, headerAccessor);

        // 검증 (Then)
        verify(chatService).sendText(eq(testRequest), eq(testSenderIp));
        verify(sendingOperations).convertAndSend(eq("/topic/chat/room/" + testRoomId), eq(expectedResponse));
    }

    @DisplayName("sendImage: 이미지 전송에 성공한다.")
    @Test
    void sendImage() {
        // 준비 (Given)
        final String testRoomId = "testRoomId";     // 테스트 채팅방 ID
        final String testSender = "testSender";     // 테스트 작성자 닉네임
        final String testSenderIp = "127.0.0.1";    // 테스트 아이피
        final String textImageUrl = "https://dolphintalk-bucket.s3.ap-northeast-2.amazonaws.com/test.jpg";  // 테스트 이미지 Url 주소
        ImageMessageRequestDTO testRequest = new ImageMessageRequestDTO(testRoomId, testSender, textImageUrl);  // 테스트 requestDTO

        ImageMessageResponseDTO expectedResponse = ImageMessageResponseDTO.builder()      // 예상 결과 객체
                .roomId(testRoomId)
                .sender(testSender)
                .senderIp("127.0.0.*")
                .imageUrl(textImageUrl)
                .build();

        when(chatService.sendImage(testRequest, testSenderIp)).thenReturn(expectedResponse);
        when(headerAccessor.getSessionAttributes()).thenReturn(Map.of("ip", testSenderIp));

        // 실행 (When)
        messageController.sendImage(testRequest, headerAccessor);

        // 검증 (Then)
        verify(chatService).sendImage(eq(testRequest), eq(testSenderIp));
        verify(sendingOperations).convertAndSend(eq("/topic/chat/room/" + testRoomId), eq(expectedResponse));
    }
}