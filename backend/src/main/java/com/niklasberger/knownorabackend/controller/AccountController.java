package com.niklasberger.knownorabackend.controller;

import com.niklasberger.knownorabackend.CommonsService;
import com.niklasberger.knownorabackend.controller.AuthController;
import com.niklasberger.knownorabackend.data.PrivateUserData;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.repo.SessionRepo;
import com.niklasberger.knownorabackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommonsService commonsService;

    @PostMapping("/public/account/my-info")
    public ResponseEntity accountMyInfo(
            @RequestBody Map<String, String> requestInput
    ) {

        String userId = requestInput.getOrDefault("userId", "").trim().replace("'", "");
        String sessionId = requestInput.getOrDefault("sessionId", "").trim().replace("'", "");
        String sessionToken = requestInput.getOrDefault("sessionToken", "").trim().replace("'", "");

        Optional<SessionData> requestSession = commonsService.validateSession(sessionId, sessionToken, userId);

        if (requestSession.isEmpty()) {
            return new ResponseEntity(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        return userRepo.findById(userId)
                .map(user -> new ResponseEntity<>(
                        new PrivateUserData(user),
                        HttpStatus.OK
                ))
                .orElseGet(() -> new ResponseEntity(
                        "[\"User not found\"]",
                        HttpStatus.NOT_FOUND
                ));

    }

}
