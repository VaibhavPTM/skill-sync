package com.work.skill.sync.service.userservice;

import com.work.skill.sync.entity.user.User;

public interface UserSkillService {
    public void addUserSkill(User user, String st);
    void removeAllSkillsByUserId(Long userId);

}
