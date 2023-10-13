package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatFileApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("uploadImage: 이미지가 정상적으로 업로드되는지 확인한다.")
    @Test
    public void uploadImage() throws Exception {
        // 준비 (Given)
        final String url = "/api/chat/image";
        byte[] testImage = "테스트용 페이크 이미지".getBytes(StandardCharsets.UTF_8);

        final MockMultipartFile multipartFile = new MockMultipartFile(
                "multipartFile",
                "test.jpg", // 테스트 파일 이름
                MediaType.IMAGE_JPEG_VALUE, // 테스트 파일 타입
                testImage // 테스트 파일 데이터
        );
        // 실행 (When)
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.multipart(url)
                .file(multipartFile)
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (Then)
        result
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl")
                        .value("https://dolphintalk-bucket.s3.ap-northeast-2.amazonaws.com/test.jpg"));
    }

}