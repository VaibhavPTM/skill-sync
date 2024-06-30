package com.work.skill.sync.entity.job;

import com.work.skill.sync.entity.skill.Skill;
import jakarta.persistence.*;

@Entity
@Table(name = "job_skill")
public class JobSkill {
    @EmbeddedId
    private JobSkillId id = new JobSkillId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jobId")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    private Skill skill;

    public JobSkill() {

    }
    public JobSkill(Job job, Skill skill) {
        this.job = job;
        this.skill = skill;
        id.setSkillId(skill.getId());
        id.setJobId(job.getId());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
