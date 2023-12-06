package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.message.UploadImageResponseDTO;
import com.example.dolphintalkserver.service.AmazonS3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChatFileApiController {
    private final AmazonS3FileService s3FileService;

    // 이미지 전송을 위한 업로드
    @PostMapping("/api/chat/image")
    public ResponseEntity<UploadImageResponseDTO> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        UploadImageResponseDTO response = s3FileService.uploadImage(multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
