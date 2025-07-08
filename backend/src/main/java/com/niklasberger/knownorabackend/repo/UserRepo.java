package com.niklasberger.knownorabackend.repo;

import com.niklasberger.knownorabackend.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserData, String> {

    @Query("SELECT u FROM UserData u WHERE u.email = ?1")
    Optional<Object> findByEmail(String cleanedEmail);

}
