package com.work.skill.sync.service.userservice;

import com.work.skill.sync.entity.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    public User findByUsername(String username);
    public User registerUser(User newUser);
    public User loginUser(String username, String password);
    public String getSkillsForUser(Long userId);

    List<User> findAllUsers();

    User updateUser(Long userId, Map<String, Object> updates);
}
