package com.work.skill.sync.entity.user;

import com.work.skill.sync.entity.skill.Skill;
import jakarta.persistence.*;

@Entity
@Table(name = "user_skills")
public class UserSkill {
    @EmbeddedId
    private UserSkillId id = new UserSkillId();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("skillId")
    private Skill skill;

    // Constructors, getters, setters
    // Omitted for brevity
    public UserSkill() {}

    public UserSkill(User user, Skill skill) {
        this.user = user;
        this.skill = skill;
        this.id.setUserId(user.getId());
        this.id.setSkillId(skill.getId());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
