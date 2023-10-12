package com.example.dolphintalkserver.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageMessageRequestDTO {
    private String roomId;
    private String sender;
    private String imageUrl;
}
