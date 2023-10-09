package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.dto.ChatRoomListResponseDTO;
import com.example.dolphintalkserver.service.ChatRoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    @PostMapping("/room")
    public String createChatRoom(ChatRoomCreateRequestDTO chatRoomCreateRequestDTO) {
        chatRoomService.createRoom(chatRoomCreateRequestDTO);
        return "chat.html";
    }

    @GetMapping("/rooms")
    public ResponseEntity<ChatRoomListResponseDTO> getChatRoomList() {
        return ResponseEntity.ok().body(chatRoomService.findAllRooms());
    }
}
