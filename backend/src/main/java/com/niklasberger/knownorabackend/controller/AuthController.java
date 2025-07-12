package com.niklasberger.knownorabackend.controller;

import com.niklasberger.knownorabackend.data.PrivateUserData;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.data.UserData;
import com.niklasberger.knownorabackend.repo.SessionRepo;
import com.niklasberger.knownorabackend.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua_parser.Parser;
import ua_parser.Client;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SessionRepo sessionRepo;

    @PostMapping("/public/auth/signup")
    public ResponseEntity signup(
            @RequestBody Map<String, String> requestInput
    ){

        String cleanedEmail = requestInput.getOrDefault("email", "").trim().toLowerCase().replace("'", "");
        String cleanedPassword = requestInput.getOrDefault("password", "").trim().replace("'", "");
        String cleanedFriendlyName = requestInput.getOrDefault("friendlyName", "").trim().replace("'", "");

        // Check if any field is empty
        if (cleanedEmail.isEmpty() || cleanedPassword.isEmpty() || cleanedFriendlyName.isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Missing required fields\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if email is valid
        if (!cleanedEmail.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
            return new ResponseEntity<>(
                    "[\"Invalid email\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if password is valid: Needs to be between 8 and 64 characters, 1 uppercase, 1 lowercase, 1 digit, and 1 special character
        if (!cleanedPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$")) {
            return new ResponseEntity<>(
                    "[\"Invalid password\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if email is already in use
        if (userRepo.findByEmail(cleanedEmail).isPresent()) {
            return new ResponseEntity<>(
                    "[\"Email already in use\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Hash the password using SHA-256
        String hashedPassword = DigestUtils.sha512Hex(cleanedPassword + cleanedEmail);

        // Create a new user
        var newUser = new UserData(
                cleanedFriendlyName,
                cleanedEmail,
                hashedPassword,
                0,
                "",
                List.of()
        );

        // Save the user to the database
        userRepo.save(newUser);

        return new ResponseEntity(new PrivateUserData(newUser), HttpStatus.CREATED);

    }

    @PostMapping("/public/auth/login")
    public ResponseEntity login(
            @RequestBody Map<String, String> requestInput,
            HttpServletRequest request
    ){
        String cleanedEmail = requestInput.getOrDefault("email", "").trim().toLowerCase().replace("'", "");
        String cleanedPassword = requestInput.getOrDefault("password", "").trim().replace("'", "");

        // Check if any field is empty
        if (cleanedEmail.isEmpty() || cleanedPassword.isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Missing required fields\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Hash the password using SHA-256
        String hashedPassword = DigestUtils.sha512Hex(cleanedPassword + cleanedEmail);

        // Find the user by email and password
        var userOpt = userRepo.findByEmail(cleanedEmail)
                .filter(u -> ((UserData) u).getPassword().equals(hashedPassword));

        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Invalid email or password\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        UserData user = (UserData) userOpt.get();

        // User is authenticated now, creating a session w/ browser info
        String userAgent = request.getHeader("User-Agent");
        Parser uaParser = new Parser();
        Client c = uaParser.parse(userAgent);
        String browser = c.userAgent.family;
        if (browser == null || browser.isEmpty() || browser.equals("Other")) {
            browser = "Generic Browser";
        }
        String os = c.os.family;
        if (os == null || os.isEmpty() || os.equals("Other")) {
            os = "Generic OS";
        }

        System.out.println(c.device.family);

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }

        SessionData sessionData = new SessionData(user.getId(), browser, os, ipAddress);
        sessionRepo.save(sessionData);

        return new ResponseEntity<>(sessionData, HttpStatus.OK);

    }

}
