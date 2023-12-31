package com.example.dolphintalkserver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Table(name = "chatrecord")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ChatRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // 사용자 닉네임
    @Column(name = "nickname", nullable = false)
    private String nickname;

    // 사용자 메세지
    @Column(name = "message", nullable = false)
    private String message;

    // 사용자 IP
    @Column(name = "ip", nullable = false)
    private String ip;

    // 사용자 메시지 보낸 시간
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public ChatRecord(String nickname, String message, String ip) {
        this.nickname = nickname;
        this.message = message;
        this.ip = ip;
    }
}
