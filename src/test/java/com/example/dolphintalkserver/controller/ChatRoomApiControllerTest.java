package com.example.dolphintalkserver.controller;

import com.example.dolphintalkserver.domain.ChatRoom;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateRequestDTO;
import com.example.dolphintalkserver.dto.chatroom.ChatRoomCreateResponseDTO;
import com.example.dolphintalkserver.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void clear() throws Exception {
        chatRoomService.deleteAllRoom();
    }

    @DisplayName("createChatRoom: 채팅방 생성에 성공한다.")
    @Test
    public void createChatRoom() throws Exception {
        // 준비 (Given)
        final String url = "/api/room";
        final ChatRoomCreateRequestDTO requestDTO = new ChatRoomCreateRequestDTO("채팅방 생성 테스트");
        String requestJson = objectMapper.writeValueAsString(requestDTO);   // 직렬화

        // 실행 (When)
        final ResultActions result = mockMvc.perform(post(url)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (Then)
        result
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomId").isString());
    }

    @DisplayName("getRoom: 채팅방 조회에 성공한다.")
    @Test
    public void getRoom() throws Exception {
        // 준비 (Given)
        final String url = "/api/room";
        final String roomName = "채팅방 데이터";

        // 실행 (When)
        final ChatRoomCreateRequestDTO requestDTO = new ChatRoomCreateRequestDTO(roomName);
        final ChatRoomCreateResponseDTO responseDTO = chatRoomService.createRoom(requestDTO);   // 데이터 생성
        final String roomId = responseDTO.getRoomId();

        final ResultActions result = mockMvc.perform(get(url)   // 데이터 가져오기
                .param("roomId", roomId)
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (Then)
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.roomName").value(roomName));
    }

    @DisplayName("getRooms: 채팅방 목록 조회에 성공한다.")
    @Test
    public void getRooms() throws Exception {
        // 준비 (Given)
        final String url = "/api/rooms";
        final String roomName = "채팅방 데이터";

        // 실행 (When)
        final ChatRoomCreateRequestDTO requestDTO = new ChatRoomCreateRequestDTO(roomName);
        final ChatRoomCreateResponseDTO responseDTO = chatRoomService.createRoom(requestDTO);   // 데이터 생성
        final String roomId = responseDTO.getRoomId();

        final ResultActions result = mockMvc.perform(get(url)   // 데이터 가져오기
                .accept(MediaType.APPLICATION_JSON));

        // 검증 (Then)
        result
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.chatRooms[0].roomName").value(roomName));
    }
}