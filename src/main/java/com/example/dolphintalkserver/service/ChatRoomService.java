package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.chatroom.*;
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
    public ChatRoomsResponseDTO findAllRooms() {
        List<ChatRoom> chatRoomList = new ArrayList<>(chatRooms.values());

        return new ChatRoomsResponseDTO(chatRoomList);
    }

    // 채팅방 정보 가져오기 기능
    public ChatRoomResponseDTO findRoom(String roomId) {
        ChatRoom chatroom = chatRooms.get(roomId);

        return new ChatRoomResponseDTO(chatroom.getRoomId(), chatroom.getRoomName());
    }

    // 채팅방 생성 기능
    public ChatRoomCreateResponseDTO createRoom(ChatRoomCreateRequestDTO request) {
        ChatRoom chatRoom = new ChatRoom(request.getRoomName());
        chatRooms.put(chatRoom.getRoomId(), chatRoom);

        return new ChatRoomCreateResponseDTO(chatRoom.getRoomId());
    }

    // 채팅방 삭제 기능
    private void deleteRoom(String roomId) {
        chatRooms.remove(roomId);
    }

    public void addUserToRoom(String roomId) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if (chatRoom != null) {
            chatRoom.increaseUserCount();
        }
    }

    public void removeUserFromRoom(String roomId) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if (chatRoom != null) {
            chatRoom.decreaseUserCount();
            if (chatRoom.getUserCount() <= 0) {
                deleteRoom(roomId);
            }
        }
    }
}
