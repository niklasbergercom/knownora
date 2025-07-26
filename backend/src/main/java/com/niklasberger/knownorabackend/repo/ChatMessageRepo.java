package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.ChatMessageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepo extends JpaRepository<ChatMessageData, Long> {
}
