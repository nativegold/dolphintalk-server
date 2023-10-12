package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.*;
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

    // 채팅방 정보 가져오기 기능
    public ChatroomResponseDTO findRoom(String roomId) {
        ChatRoom chatroom = chatRooms.get(roomId);

        return new ChatroomResponseDTO(chatroom.getRoomId(), chatroom.getRoomName());
    }

    // 채팅방 삭제 기능
    public void deleteRoom(ChatRoomDeleteRequestDTO request) {
        chatRooms.remove(request.getRoomId());
    }

    // 채팅방 생성 기능
    public ChatRoomCreateResponseDTO createRoom(ChatRoomCreateRequestDTO request) {
        ChatRoom chatRoom = new ChatRoom(request.getRoomName());
        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        return new ChatRoomCreateResponseDTO(chatRoom.getRoomId());
    }
}
