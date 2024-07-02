package com.work.skill.sync.controller;

import com.work.skill.sync.entity.chat.Message;
import com.work.skill.sync.service.chatservice.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody Map<String, Object> mp) {
        Long user1Id = Long.valueOf(mp.get("user1Id").toString());
        Long user2Id = Long.valueOf(mp.get("user2Id").toString());
        try {
            String content = mp.get("content").toString();
            Message message = chatService.sendMessage(user1Id, user2Id, content);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<String>> getFormattedMessages(@RequestBody Map<String, Object> mp) {
        Long user1Id = Long.valueOf(mp.get("user1Id").toString());
        Long user2Id = Long.valueOf(mp.get("user2Id").toString());
        try {
            List<String> messages = chatService.getFormattedMessages(user1Id, user2Id);
            return ResponseEntity.status(HttpStatus.CREATED).body(messages);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
