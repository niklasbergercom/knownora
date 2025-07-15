package com.niklasberger.knownorabackend.controller;

import com.niklasberger.knownorabackend.CommonsService;
import com.niklasberger.knownorabackend.data.GenericConfigData;
import com.niklasberger.knownorabackend.data.PrivateUserData;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.data.UserData;
import com.niklasberger.knownorabackend.repo.GenericConfigRepo;
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
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private GenericConfigRepo genericConfigRepo;

    @Autowired
    private CommonsService commonsService;

    @GetMapping("/private/auth/signup-domain")
    public ResponseEntity getSignupEmail() {

        // Check if signUpDomain key exists in genericConfigRepo, else return null
        String signUpDomain = genericConfigRepo.findByKey("signUpDomain")
                .map(GenericConfigData::getValue)
                .orElse("null");

        return new ResponseEntity(signUpDomain, HttpStatus.OK);

    }

    @GetMapping("/private/auth/signup-grades")
    public ResponseEntity getSignupGrades() {

        // Check if signUpGrades key exists in genericConfigRepo, else return null
        String signUpGrades = genericConfigRepo.findByKey("signUpGrades")
                .map(GenericConfigData::getValue)
                .orElse("[]");

        return new ResponseEntity(signUpGrades, HttpStatus.OK);

    }

    @PostMapping("/public/auth/signup")
    public ResponseEntity signup(
            @RequestBody Map<String, String> requestInput
    ){

        String cleanedEmail = requestInput.getOrDefault("email", "").trim().toLowerCase().replace("'", "");
        String cleanedPassword = requestInput.getOrDefault("password", "").trim().replace("'", "");
        String cleanedFriendlyName = requestInput.getOrDefault("friendlyName", "").trim().replace("'", "");
        String cleanedGrade = requestInput.getOrDefault("grade", "").trim().replace("'", "");

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

        if (!cleanedGrade.isEmpty()) {

            // Check if signUpGrades key exists in genericConfigRepo, else return null
            String signUpGrades = genericConfigRepo.findByKey("signUpGrades")
                    .map(GenericConfigData::getValue)
                    .orElse("[]");

            // Parse the signUpGrades JSON array
            List<String> signUpGradesList;
            try {
                signUpGradesList = commonsService.parseJsonArray(signUpGrades);
            } catch (Exception e) {
                System.err.println("Error parsing signUpGrades JSON: " + e.getMessage());
                return new ResponseEntity<>(
                        "[\"Internal Server Error\"]",
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }

            signUpGradesList.add("0"); // Add "0" to the list of allowed grades, which represents "No grade"

            // Check if the provided grade is in the list of allowed grades
            if (!signUpGradesList.contains(cleanedGrade)) {
                return new ResponseEntity<>(
                        "[\"Invalid grade\"]",
                        HttpStatus.BAD_REQUEST
                );
            }

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
                List.of(),
                0,
                cleanedGrade
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

        if (user.getStatus() == 0) {
            return new ResponseEntity<>(
                    "[\"Account is not activated yet. Check your emails for your activation link or <a href='/app/login/resend-code' class='underlined-link' style='display: inline-block; color: red;'>request a new one<div></div></a>.\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (user.getStatus() == 2) {
            return new ResponseEntity<>(
                    "[\"Account is deactivated. Please contact support.\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

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

        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }

        SessionData sessionData = new SessionData(user.getId(), browser, os, ipAddress);
        sessionRepo.save(sessionData);

        return new ResponseEntity<>(sessionData, HttpStatus.OK);

    }

    @PostMapping("/public/auth/logout")
    public ResponseEntity logout(
            @RequestBody Map<String, String> requestInput
    ){

        String userId = requestInput.getOrDefault("userId", "").trim().replace("'", "");
        String sessionId = requestInput.getOrDefault("sessionId", "").trim().replace("'", "");
        String sessionToken = requestInput.getOrDefault("sessionToken", "").trim().replace("'", "");

        Optional<SessionData> requestSession = commonsService.validateSession(sessionId, sessionToken, userId).getFirst();

        if (requestSession.isEmpty()) {
            return new ResponseEntity(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        // Delete the session
        sessionRepo.delete(requestSession.get());

        return new ResponseEntity<>(
                "[\"Logged out successfully\"]",
                HttpStatus.OK
        );

    }

    public Boolean checkSession(
            String userId,
            String sessionId,
            String sessionToken
    ) {
        Long sessionIdLong;
        try {
            sessionIdLong = Long.parseLong(sessionId);
        } catch (NumberFormatException e) {
            return false;
        }
        return sessionRepo.findById(sessionIdLong)
                .filter(session -> session.getToken().equals(sessionToken))
                .filter(session -> session.getUserId().equals(userId))
                .filter(session -> session.getValidUntil().after(new java.util.Date()))
                .isPresent();
    }

}
