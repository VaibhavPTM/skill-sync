package com.work.skill.sync.controller;

import com.work.skill.sync.entity.address.Address;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.entity.user.UserSkill;
import com.work.skill.sync.service.skillserivece.SkillService;
import com.work.skill.sync.service.userservice.UserService;
import com.work.skill.sync.service.userservice.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private SkillService skillService;
    private UserSkillService userSkillService;

    @Autowired
    public UserController(UserService userService, SkillService skillService, UserSkillService userSkillService) {
        this.userService = userService;
        this.skillService = skillService;
        this.userSkillService = userSkillService;
    }

    // get a list of user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.findAllUsers();
            for(User user : users) {
                user.setSk(userService.getSkillsForUser(user.getId()));
            }
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // register user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String,Object> body) {
        try {
//            User savedUser = userService.registerUser(newUser);
            User newUser = new User();
            newUser.setUsername(body.get("username").toString());
            newUser.setPassword(body.get("password").toString());
            newUser.setEmail(body.get("email").toString());
            newUser.setPhotoUrl(body.get("photoUrl").toString());
            newUser.setAge(Integer.valueOf(body.get("age").toString()));
            newUser.setDescription(body.get("description").toString());
            String sk = body.get("skills").toString();
            newUser.setSk(sk);
            Address address = new Address();
            address.setCity(body.get("city").toString());
            address.setCountry(body.get("country").toString());
            address.setPostalCode(body.get("postal_code").toString());
            address.setState(body.get("state").toString());
            address.setStreet(body.get("street").toString());
            newUser.setAddress(address);
            String [] tmp = sk.split(",");
            {
                // add userdata to user table
                userService.registerUser(newUser);
            }
            {
                // add skills to skills table.
                skillService.addSkills(sk);
            }
            {
                // add skills to user_skills table
                userSkillService.addUserSkill(newUser, sk);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
            User u1 = new User();
            u1.setDescription(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u1);
        }
    }

    // login user
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, Object> body) {
        try {
            User newUser = userService.loginUser(body.get("username").toString(), body.get("password").toString());
            Set<UserSkill> skills = newUser.getSkills();
            String userSKills = userService.getSkillsForUser(newUser.getId());
            newUser.setSk(userSKills);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
            User u1 = new User();
            u1.setDescription(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u1);
        }
    }

    // get user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username) {
        try {
            User user = userService.findByUsername(username);
            user.setSk(userService.getSkillsForUser(user.getId()));
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            User u1 = new User();
            u1.setDescription(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u1);
        }
    }

    @GetMapping("/search/{skillId}")
    public ResponseEntity<List<User>> searchUserBySkillId(@PathVariable Long skillId) {
        try {
            List<User> userList = userService.searchUserBySkillId(skillId);
            return ResponseEntity.ok(userList);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // update user by userid
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        try {
            User updatedUser = userService.updateUser(userId, updates);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            System.out.println(e.toString());
            User u1 = new User();
            u1.setDescription(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u1);
        }
    }




    // it is just a testing api you can ignore it
    @GetMapping("/test")
    public String testing() {
        return "This is working!!!";
    }
}
