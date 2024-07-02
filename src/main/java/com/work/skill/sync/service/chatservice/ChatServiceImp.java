package com.work.skill.sync.service.chatservice;

import com.work.skill.sync.entity.chat.ChatRoom;
import com.work.skill.sync.entity.chat.Message;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.repository.chatrepo.ChatRoomRepository;
import com.work.skill.sync.repository.chatrepo.MessageRepository;
import com.work.skill.sync.repository.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImp implements ChatService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    public ChatRoom getOrCreateChatRoom(Long userId1, Long userId2) {
        User user1 = userRepository.findById(userId1).orElseThrow(() -> new RuntimeException("User1 not found"));
        User user2 = userRepository.findById(userId2).orElseThrow(() -> new RuntimeException("User2 not found"));

        Optional<ChatRoom> chatRoom = chatRoomRepository.findByUser1AndUser2OrUser2AndUser1(user1, user2, user1, user2);
        return chatRoom.orElseGet(() -> {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setUser1(user1);
            newChatRoom.setUser2(user2);
            return chatRoomRepository.save(newChatRoom);
        });
    }

    @Override
    public Message sendMessage(Long user1Id, Long user2Id, String content) {
        User sender = userRepository.findById(user1Id).orElseThrow(() -> new RuntimeException("User1 not found"));
        User receiver = userRepository.findById(user2Id).orElseThrow(() -> new RuntimeException("User2 not found"));
        ChatRoom chatRoom = getOrCreateChatRoom(user1Id, user2Id);
        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setContent(content);

        return messageRepository.save(message);
    }
    public List<Message> getMessages(Long userId1, Long userId2) {
        ChatRoom chatRoom = getOrCreateChatRoom(userId1, userId2);
        return messageRepository.findByChatRoom(chatRoom);
    }

    @Override
    public List<String> getFormattedMessages(Long user1Id, Long user2Id) {
        List<Message> messages = getMessages(user1Id, user2Id);
        return messages.stream()
                .map(message -> message.getSender().getUsername() + " : " + message.getContent())
                .collect(Collectors.toList());
    }
}
