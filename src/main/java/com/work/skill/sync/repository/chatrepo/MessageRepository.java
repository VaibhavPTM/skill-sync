package com.work.skill.sync.repository.chatrepo;

import com.work.skill.sync.entity.chat.ChatRoom;
import com.work.skill.sync.entity.chat.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoom(ChatRoom chatRoom);
}
