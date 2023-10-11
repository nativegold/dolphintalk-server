package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.ChatRoomsRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatViewController {   // View 전환에 사용하는 컨트롤러

    @GetMapping("/rooms")
    public String getChatRooms() {
        return "chatrooms";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/room/{chatRoomId}")
    public String joinChatroom(@PathVariable String chatRoomId) {
        return "chatroom";
    }
}
