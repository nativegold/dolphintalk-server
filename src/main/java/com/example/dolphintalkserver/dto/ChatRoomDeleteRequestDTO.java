package com.example.dolphintalkserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class ChatRoomDeleteRequestDTO {
    private String roomId;
}
