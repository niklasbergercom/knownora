package com.niklasberger.knownorabackend.controller;

import com.niklasberger.knownorabackend.CommonsService;
import com.niklasberger.knownorabackend.data.ChatData;
import com.niklasberger.knownorabackend.data.ChatMessageData;
import com.niklasberger.knownorabackend.data.SessionData;
import com.niklasberger.knownorabackend.repo.ChatMessageRepo;
import com.niklasberger.knownorabackend.repo.ChatRepo;
import com.niklasberger.knownorabackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class ChatController {

    @Autowired
    private CommonsService commonsService;

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/public/chat/get-my-chats")
    public ResponseEntity getMyChats(
            @RequestBody Map<String, String> requestInput
    ){

        String userId = requestInput.getOrDefault("userId", "").trim().replace("'", "");
        String sessionId = requestInput.getOrDefault("sessionId", "").trim().replace("'", "");
        String sessionToken = requestInput.getOrDefault("sessionToken", "").trim().replace("'", "");
        String limit = requestInput.getOrDefault("limit", "").trim().replace("'", "");

        Pair<Optional<SessionData>, String> requestSession = commonsService.validateSession(sessionId, sessionToken, userId);

        if (requestSession.getSecond().equals("expired")) {
            return new ResponseEntity<>(
                    "[\"Session expired\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (requestSession.getFirst().isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        try {
            Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(
                    "[\"Invalid limit\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        List<ChatData> requestedConversations = chatRepo.findChatsByUserIdOrderedByLastMessageLimited(userId, limit);

        List<Map<String, String>> returnObject = new ArrayList<>(List.of());

        // Loop over every chat
        for (ChatData chat : requestedConversations) {
            // If the chat is private, set the name to the other participant's friendly name
            if (chat.getType().equals("private") && chat.getParticipants().size() == 2) {
                String otherParticipantId = chat.getParticipants().stream()
                        .filter(participant -> !participant.equals(userId))
                        .findFirst()
                        .orElse("");
                if (!otherParticipantId.isEmpty()) {
                    chat.setName(userRepo.findById(otherParticipantId)
                            .map(user -> user.getFriendlyName())
                            .orElse("Unknown User"));
                    chat.setPicture(userRepo.findById(otherParticipantId)
                            .map(user -> user.getPicture())
                            .orElse("/assets/images/account-default.svg"));
                    if (chat.getPicture() == null || chat.getPicture().isEmpty()) {
                        chat.setPicture("/assets/images/account-default.svg");
                    }
                }
            }

            String preview = "";

            Optional<ChatMessageData> lastMessageOptional = chatMessageRepo.findByChatIdOrderdByTimestamp(chat.getId());

            if (lastMessageOptional.isEmpty()) {
                preview = "No message yet";
            } else {
                ChatMessageData lastMessage = lastMessageOptional.get();
                if (lastMessage.getType().equals("text")) {
                    preview = lastMessage.getContent();
                } else if (lastMessage.getType().equals("image")) {
                    preview = "Image";
                } else {
                    preview = lastMessage.getContent();
                }
            }

            returnObject.add(Map.of(
                    "id", chat.getId().toString(),
                    "name", chat.getName(),
                    "type", chat.getType(),
                    "picture", chat.getPicture(),
                    "description", chat.getDescription(),
                    "preview", preview,
                    "lastMessage", chat.getLastMessage().toString(),
                    "participants", String.join(", ", chat.getParticipants())
            ));

        }

        return new ResponseEntity<>(
                returnObject,
                HttpStatus.OK
        );

    }

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
            return new ResponseEntity<>(
                    "[\"Session expired\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (requestSession.getFirst().isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (userId.equals(recipientId)) {
            return new ResponseEntity<>(
                    "[\"You can't create a conversation with yourself.\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if the recipient exists
        if (!userRepo.existsById(recipientId)) {
            return new ResponseEntity<>(
                    "[\"Recipient user not found.\"]",
                    HttpStatus.NOT_FOUND
            );
        }

        // Check if a private chat between both users already exists
        if (chatRepo.existsPrivateChatBetweenUsers(userId, recipientId)) {
            return new ResponseEntity<>(
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

        return new ResponseEntity<>(
                "[\"" + createdChat.getId().toString() + "\"]",
                HttpStatus.OK
        );

    }

    // TODO: Implement sending messages
    @PostMapping("/public/chat/send-message")
    public ResponseEntity sendMessage(
            @RequestBody Map<String, String> requestInput
    ){

        String userId = requestInput.getOrDefault("userId", "").trim().replace("'", "");
        String sessionId = requestInput.getOrDefault("sessionId", "").trim().replace("'", "");
        String sessionToken = requestInput.getOrDefault("sessionToken", "").trim().replace("'", "");
        String conversationId = requestInput.getOrDefault("conversationId", "").trim().replace("'", "");
        String messageType = requestInput.getOrDefault("messageType", "").trim().replace("'", "");
        String messageContent = requestInput.getOrDefault("messageContent", "").trim().replace("'", "");

        Pair<Optional<SessionData>, String> requestSession = commonsService.validateSession(sessionId, sessionToken, userId);

        if (requestSession.getSecond().equals("expired")) {
            return new ResponseEntity<>(
                    "[\"Session expired\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (requestSession.getFirst().isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Invalid sessionId, sessionToken or userId\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        try {
            Long.parseLong(conversationId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(
                    "[\"Invalid conversationId format\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (!List.of("text", "image").contains(messageType)) {
            return new ResponseEntity<>(
                    "[\"Invalid messageType found\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (messageContent.equals("image")) {
            return new ResponseEntity<>(
                    "[\"messageType image is not implemented yet\"]",
                    HttpStatus.NOT_IMPLEMENTED
            );
        }

        if (messageContent.isEmpty()) {
            return new ResponseEntity<>(
                    "[\"messageContent cannot be empty\"]",
                    HttpStatus.BAD_REQUEST
            );
        }

        Long conversationIdLong = Long.parseLong(conversationId);
        Optional<ChatData> requestedConversationOptional = chatRepo.findById(conversationIdLong);

        if (requestedConversationOptional.isEmpty()) {
            return new ResponseEntity<>(
                    "[\"Invalid conversation\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        ChatData requestedConversation = requestedConversationOptional.get();

        if (!requestedConversation.getParticipants().contains(userId)) {
            return new ResponseEntity<>(
                    "[\"Invalid conversation\"]",
                    HttpStatus.UNAUTHORIZED
            );
        }

        ChatMessageData createdMessage = chatMessageRepo.save(
                new ChatMessageData(
                        conversationIdLong,
                        userId,
                        messageType,
                        messageContent
                )
        );

        requestedConversation.getMessages().add(createdMessage.getId());
        requestedConversation.setLastMessage(new Timestamp(System.currentTimeMillis()));
        chatRepo.save(requestedConversation);

        return new ResponseEntity<>(
                createdMessage,
                HttpStatus.OK
        );

    }

}
