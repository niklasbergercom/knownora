package com.niklasberger.knownorabackend.data;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class ChatData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String creatorId;
    private String name; // If empty, it's the name of the other user(s)
    private String type; // e.g., "private", "group"
    private String picture; // URL to the chat picture, if any
    private String description; // Optional description of the chat
    @ElementCollection
    private List<String> participants; // List of user IDs participating in the chat
    private Timestamp lastMessage;
    @ElementCollection
    private List<Long> messages;

    public ChatData(String creatorId, String name, String type, String picture, String description, List<String> participants) {
        this.creatorId = creatorId;
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.description = description;
        this.participants = participants;
        this.lastMessage = new Timestamp(System.currentTimeMillis());
    }

    public ChatData() {

    }
}
