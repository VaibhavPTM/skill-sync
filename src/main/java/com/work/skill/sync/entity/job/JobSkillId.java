package com.work.skill.sync.entity.job;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class JobSkillId {
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "skill_id")
    private Long skillId;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId)  {
        this.skillId = skillId;
    }

    @Override
    public String toString() {
        return "JobSkillId{" +
                "jobId=" + jobId +
                ", skillId=" + skillId +
                '}';
    }
}
