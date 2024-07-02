package com.work.skill.sync.service.chatservice;

import com.work.skill.sync.entity.chat.Message;

import java.util.List;

public interface ChatService {
    Message sendMessage(Long user1Id, Long user2Id, String content);

    List<String> getFormattedMessages(Long user1Id, Long user2Id);
}
