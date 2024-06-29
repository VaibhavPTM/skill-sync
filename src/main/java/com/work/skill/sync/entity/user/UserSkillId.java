package com.work.skill.sync.entity.user;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserSkillId {
    private Long userId;
    private Long skillId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "UserSkillId{" +
                "userId=" + userId +
                ", skillId=" + skillId +
                '}';
    }
}
