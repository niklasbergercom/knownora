package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.ChatMessageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatMessageRepo extends JpaRepository<ChatMessageData, Long> {

    @Query("SELECT c FROM ChatMessageData c WHERE c.chatId = :chatId ORDER BY timestamp DESC LIMIT 1")
    Optional<ChatMessageData> findByChatIdOrderdByTimestamp(
            @Param("chatId") Long chatId
    );

}
