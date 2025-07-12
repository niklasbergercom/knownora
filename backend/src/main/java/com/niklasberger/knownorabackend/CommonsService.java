package com.niklasberger.knownorabackend;

import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonsService {

    @Autowired
    private SessionRepo sessionRepo;

    public Optional<SessionData> validateSession(String sessionId, String sessionToken, String userId) {
        Long sessionIdLong;
        try {
            sessionIdLong = Long.parseLong(sessionId);
        } catch (NumberFormatException e) {
            return null;
        }
        return sessionRepo.findById(sessionIdLong)
                .filter(session -> session.getToken().equals(sessionToken))
                .filter(session -> session.getUserId().equals(userId))
                .filter(session -> session.getValidUntil().after(new java.util.Date()));
    }

}
