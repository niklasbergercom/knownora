package com.niklasberger.knownorabackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommonsService {

    @Autowired
    private SessionRepo sessionRepo;

    public Pair<Optional<SessionData>, String> validateSession(String sessionId, String sessionToken, String userId) {
        Long sessionIdLong;
        try {
            sessionIdLong = Long.parseLong(sessionId);
        } catch (NumberFormatException e) {
            return Pair.of(Optional.empty(), "invalid");
        }

        Optional<SessionData> requestedSession = sessionRepo.findById(sessionIdLong)
                .filter(session -> session.getToken().equals(sessionToken))
                .filter(session -> session.getUserId().equals(userId));

        if (requestedSession.isEmpty()) {
            return Pair.of(Optional.empty(), "invalid");
        }

        if (requestedSession.get().getValidUntil().getTime() < new java.util.Date().getTime()) {
            return Pair.of(Optional.empty(), "expired");
        }

        return Pair.of(requestedSession, "valid");
    }

    public List<String> parseJsonArray(String jsonArray) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonArray, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
    }

}
