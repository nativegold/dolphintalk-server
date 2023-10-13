package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateResponseDTO;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomsResponseDTO;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomResponseDTO;
import com.example.dolphintalkserver.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatRoomApiController {    // 채팅방 관련 RESTful API 컨트롤러
    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity<ChatRoomCreateResponseDTO> createChatRoom(@RequestBody ChatRoomCreateRequestDTO request) {
        ChatRoomCreateResponseDTO response = chatRoomService.createRoom(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 채팅방 정보 반환
    @GetMapping("/room")
    public ResponseEntity<ChatRoomResponseDTO> getRoom(@RequestParam String roomId) {
        ChatRoomResponseDTO response = chatRoomService.findRoom(roomId);

        if(response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 채팅방 목록 반환
    @GetMapping("/rooms")
    public ResponseEntity<ChatRoomsResponseDTO> getChatRoomList() {
        ChatRoomsResponseDTO response = chatRoomService.findAllRooms();

        return ResponseEntity.ok().body(response);
    }
}
