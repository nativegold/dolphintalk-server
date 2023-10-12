package com.example.dolphintalkserver.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LeaveMessageRequestDTO {
    private String roomId;
    private String sender;
}
