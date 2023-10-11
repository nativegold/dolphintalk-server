package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.dto.ChatRoomCreateResponseDTO;
import com.example.dolphintalkserver.dto.ChatRoomDeleteRequestDTO;
import com.example.dolphintalkserver.dto.ChatRoomListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    // 현재 활성화된 채팅방
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    // 채팅방 목록 가져오기 기능
    public ChatRoomListResponseDTO findAllRooms() {
        List<ChatRoom> chatRoomList = new ArrayList<>(chatRooms.values());

        return new ChatRoomListResponseDTO(chatRoomList);
    }

    // 채팅방 삭제 기능
    public void deleteRoom(ChatRoomDeleteRequestDTO request) {
        chatRooms.remove(request.getRoomId());
    }

    // 채팅방 생성 기능
    public ChatRoomCreateResponseDTO createRoom(ChatRoomCreateRequestDTO request) {
        ChatRoom chatRoom = new ChatRoom(request.getChatRoomName());
        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        return new ChatRoomCreateResponseDTO(chatRoom.getRoomId());
    }
}
