package com.work.skill.sync.repository.userrepo;

import com.work.skill.sync.entity.user.UserSkill;
import com.work.skill.sync.entity.user.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {
    void deleteByUserId(Long userId);
}
