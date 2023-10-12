package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateResponseDTO;
import com.example.dolphintalkserver.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatRoomApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ChatRoomService chatRoomService;

    private String createdRoomId;
    private String createdRoomName;

    @BeforeEach
    public void setMockMvc() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        final String createUrl = "/api/room";
        final ChatRoomCreateRequestDTO requestDTO = new ChatRoomCreateRequestDTO("채팅방 데이터");
        String requestJson = objectMapper.writeValueAsString(requestDTO);

        MvcResult mvcResult = mockMvc.perform(post(createUrl)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // 응답 본문에서 채팅방 ID 추출
        String responseString = mvcResult.getResponse().getContentAsString();
        ChatRoomCreateResponseDTO responseDTO = objectMapper.readValue(responseString, ChatRoomCreateResponseDTO.class);
        String createdRoomId = responseDTO.getRoomId();

        // createdRoomId를 테스트 메서드에서 사용할 수 있도록 저장
        this.createdRoomId = createdRoomId;
    }

    @DisplayName("createChatRoom: 채팅방 생성에 성공한다.")
    @Test
    public void createChatRoom() throws Exception {
        // given
        final String url = "/api/room";
        final ChatRoomCreateRequestDTO requestDTO = new ChatRoomCreateRequestDTO("채팅방 생성 테스트");
        String requestJson = objectMapper.writeValueAsString(requestDTO);

        // when
        final ResultActions result = mockMvc.perform(post(url)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomId").isString());
    }

    @DisplayName("getRoom: 채팅방 정보를 가져온다.")
    @Test
    public void getRoom() throws Exception {
        // given
        final String url = "/api/room";
        final String roomId = this.createdRoomId;
        final String roomName = this.createdRoomName;

        // when

        final ResultActions result = mockMvc
                .perform(get(url)
                .param(roomId)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomName").value(roomName));
    }
}