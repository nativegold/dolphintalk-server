package com.example.dolphintalkserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomDeleteRequestDTO {
    private String roomId;
}