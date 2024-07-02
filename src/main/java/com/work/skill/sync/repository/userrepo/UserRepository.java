package com.work.skill.sync.repository.userrepo;

import com.work.skill.sync.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.skills us WHERE us.skill.id = :skillId")
    List<User> findUserBySkillId(Long skillId);
}