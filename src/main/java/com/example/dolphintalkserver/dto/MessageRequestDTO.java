package com.example.dolphintalkserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MessageRequestDTO {
    private String roomId;
    private String sender;
    private String message;
}
