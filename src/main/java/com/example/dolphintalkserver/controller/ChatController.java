package com.example.dolphintalkserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ChatController {

    @GetMapping("/chat/join")
    public ResponseEntity<Void> join() {
        return ResponseEntity.ok().build();
    }
}
