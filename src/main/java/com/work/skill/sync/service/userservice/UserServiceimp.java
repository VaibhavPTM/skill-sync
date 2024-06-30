package com.work.skill.sync.service.userservice;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.repository.skillrepo.SkillRepository;
import com.work.skill.sync.repository.userrepo.UserRepository;
import com.work.skill.sync.service.skillserivece.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceimp implements UserService {

    private final UserRepository userRepository;
    private SkillRepository skillRepository;
    private SkillService skillService;
    private UserSkillService userSkillService;

    @Autowired
    public UserServiceimp(UserRepository userRepository, SkillRepository skillRepository, UserSkillService userSkillServiceImp, SkillService skillService) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillService = userSkillServiceImp;
        this.skillService = skillService;
    }


    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User with username " + username + " does not exist");
        }
        return user;
    }

    public User findUserById(Long Id) {
        Optional<User> user = userRepository.findById(Id);
        if(user.get() == null) {
            throw new RuntimeException("User with userid " + Id + " does not exist");
        }
        return user.get();
    }

    public User registerUser(User newUser) {
        String username = newUser.getUsername();
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("User with username " + username + " already exists.");
        }
        // Additional business logic for registration (e.g., password hashing)
        return userRepository.save(newUser);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("User with username " + username + " does not exist or invalid credentials.");
        }
        return user;
    }

    public String getSkillsForUser(Long userId) {
        List<String> skills = skillRepository.findSkillsByUserId(userId);
        return String.join(", ", skills);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long userId, Map<String, Object> updates) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (updates.containsKey("username")) {
            user.setUsername(updates.get("username").toString());
        }
        if (updates.containsKey("password")) {
            user.setPassword(updates.get("password").toString());
        }
        if (updates.containsKey("email")) {
            user.setEmail(updates.get("email").toString());
        }
        if (updates.containsKey("photoUrl")) {
            user.setPhotoUrl(updates.get("photoUrl").toString());
        }
        if (updates.containsKey("age")) {
            user.setAge(Integer.valueOf(updates.get("age").toString()));
        }
        if (updates.containsKey("description")) {
            user.setDescription(updates.get("description").toString());
        }
        if (updates.containsKey("sk")) {
            String sk = updates.get("sk").toString();
            userSkillService.removeAllSkillsByUserId(userId);
            {
                // add skills to skills table.
                skillService.addSkills(sk);
            }
            {
                // add skills to user_skills table
                userSkillService.addUserSkill(user, sk);
            }
            user.setSk(sk);
        }
        return userRepository.save(user);
    }
}
