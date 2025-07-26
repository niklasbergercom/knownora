package com.niklasberger.knownorabackend.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class ChatMessageData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long chatId;
    private String senderId;
    private String type; // e.g., "text", "image", "file"
    private String content;
    private Timestamp timestamp;

    public ChatMessageData(Long chatId, String senderId, String type, String content, Timestamp timestamp) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    };

    public ChatMessageData(Long chatId, String senderId, String type, String content) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.type = type;
        this.content = content;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    };

    public ChatMessageData() {

    };

}
