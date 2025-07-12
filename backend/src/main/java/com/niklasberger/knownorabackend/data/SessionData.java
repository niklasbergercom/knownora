package com.niklasberger.knownorabackend.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class SessionData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String token;
    private Timestamp validUntil;
    private String friendlyName;
    private String browser = "Generic Browser";
    private String os = "Generic OS";
    private String ip;

    public SessionData(String userId) {
        this.userId = userId;
        this.token = generateSessionToken();
        this.validUntil = new Timestamp(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000); // Valid for 7 days
        this.friendlyName = "A Generic Device";
        this.ip = null;
    }

    public SessionData(String userId, String browser, String os, String ip) {
        this.userId = userId;
        this.token = generateSessionToken();
        this.validUntil = new Timestamp(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000); // Valid for 7 days
        this.browser = browser;
        this.os = os;
        this.ip = ip;
    }

    public SessionData() {

    }

    private String generateSessionToken() {
        // Generate a random token for the session
        return java.util.UUID.randomUUID() + "-" +
                java.util.UUID.randomUUID() + "-" +
                java.util.UUID.randomUUID() + "-" +
                java.util.UUID.randomUUID() + "-" +
                java.util.UUID.randomUUID();
    }

}
