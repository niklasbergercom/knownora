package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.SessionData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<SessionData, Long> {



}
