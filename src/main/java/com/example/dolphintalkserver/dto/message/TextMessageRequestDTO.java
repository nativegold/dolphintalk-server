package com.example.dolphintalkserver.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextMessageRequestDTO {
    private String roomId;
    private String sender;
    private String content;
}
