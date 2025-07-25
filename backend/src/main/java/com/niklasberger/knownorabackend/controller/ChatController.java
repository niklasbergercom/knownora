package com.niklasberger.knownorabackend.controller;

import com.niklasberger.knownorabackend.CommonsService;
import com.niklasberger.knownorabackend.data.ChatData;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.repo.ChatRepo;
import com.niklasberger.knownorabackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChatController {

//    @MessageMapping("chat.sendMessage")
//    @SendTo("/public/chat-topic/messages")
//    public Message sendMessage(Message message) {
//        // Logic to send a message
//        System.out.println("Sending message: " + message);
//
//        return message;
//    }

    @Autowired
    private CommonsService commonsService;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/public/chat/create-private-chat")
    public ResponseEntity createPrivateChat(
            @RequestBody Map<String, String> requestInput
    ){
        String userId = requestInput.getOrDefault("userId", "").trim().replace("'", "");
        String sessionId = requestInput.getOrDefault("sessionId", "").trim().replace("'", "");
        String sessionToken = requestInput.getOrDefault("sessionToken", "").trim().replace("'", "");
        String recipientId = requestInput.getOrDefault("recipientId", "").trim().replace("'", "");

        Pair<Optional<SessionData>, String> requestSession = commonsService.validateSession(sessionId, sessionToken, userId);

        if (requestSession.getSecond().equals("expired")) {
            return new ResponseEntity(
                    "[\"Session expired\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (requestSession.getFirst().isEmpty()) {
            return new ResponseEntity(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (userId.equals(recipientId)) {
            return new ResponseEntity(
                    "[\"You can't create a conversation with yourself.\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if the recipient exists
        if (!userRepo.existsById(recipientId)) {
            return new ResponseEntity(
                    "[\"Recipient user not found.\"]",
                    HttpStatus.NOT_FOUND
            );
        }

        // Check if a private chat between both users already exists
        if (chatRepo.existsPrivateChatBetweenUsers(userId, recipientId)) {
            return new ResponseEntity(
                    "[\"Chat already exists between these users.\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        ChatData newChat = new ChatData(
                userId,
                "",
                "private",
                "",
                "",
                List.of(userId, recipientId)
        );

        ChatData createdChat = chatRepo.save(newChat);

        return new ResponseEntity(
                "[\"" + createdChat.getId().toString() + "\"]",
                HttpStatus.OK
        );

    }

    // TODO: Implement sending messages
//    @PostMapping("/public/chat/send-message")
//    public ResponseEntity sendMessage(
//            @RequestBody Map<String, String> requestInput
//    ){
//        String senderId = requestInput.get("senderId");
//        String
//    }

}
