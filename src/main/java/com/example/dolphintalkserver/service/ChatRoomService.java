package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.ChatRoomCreateRequestDTO;
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
    private final Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    public ChatRoomListResponseDTO findAllRooms() {
        List<ChatRoom> chatRoomList = new ArrayList<>(chatRooms.values());

        return new ChatRoomListResponseDTO(chatRoomList);
    }

    public void deleteRoom(ChatRoomDeleteRequestDTO request) {
        chatRooms.remove(request.getRoomId());
    }

    public String createRoom(ChatRoomCreateRequestDTO request) {
        ChatRoom chatRoom = new ChatRoom(request.getChatRoomName());
        chatRooms.put(chatRoom.getRoomName(), chatRoom);

        return chatRoom.getRoomId();
    }
}
