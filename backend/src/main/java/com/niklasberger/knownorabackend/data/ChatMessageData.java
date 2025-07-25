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

}
