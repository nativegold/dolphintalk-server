package com.example.dolphintalkserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    @PostMapping("/chat/create")
    public String createChatRoom() {

        return "chat.html";
    }
}
