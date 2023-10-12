package com.example.dolphintalkserver.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TextMessageRequestDTO {
    private String roomId;
    private String sender;
    private String content;
}
