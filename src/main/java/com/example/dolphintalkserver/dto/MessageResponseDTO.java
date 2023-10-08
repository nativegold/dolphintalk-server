package com.example.dolphintalkserver.dto;

import lombok.Getter;

@Getter
public class MessageResponseDTO {
    private String nickname;
    private String message;
    private String ip;
}
