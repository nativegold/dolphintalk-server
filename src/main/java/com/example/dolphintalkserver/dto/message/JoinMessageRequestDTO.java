package com.example.dolphintalkserver.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinMessageRequestDTO {
    private String roomId;
    private String sender;
}
