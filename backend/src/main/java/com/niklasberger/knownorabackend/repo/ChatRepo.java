package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.ChatData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepo extends JpaRepository<ChatData, Long> {

    // .existsByUserIdAndRecipientId(Long userId, Long recipientId)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ChatData c WHERE SIZE(c.participants) = 2 AND :userId MEMBER OF c.participants AND :recipientId MEMBER OF c.participants")
    boolean existsPrivateChatBetweenUsers(@Param("userId") String userId, @Param("recipientId") String recipientId);

    @Query("SELECT c FROM ChatData c WHERE :userId MEMBER OF c.participants ORDER BY c.lastMessage DESC LIMIT :limit")
    List<ChatData> findChatsByUserIdOrderedByLastMessageLimited(
            @Param("userId") String userId,
            @Param("limit") String limit
    );

}
