package com.work.skill.sync.repository.chatrepo;

import com.work.skill.sync.entity.chat.ChatRoom;
import com.work.skill.sync.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByUser1AndUser2OrUser2AndUser1(User user1, User user2, User user11, User user21);
}
