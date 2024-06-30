package com.work.skill.sync.service.jobservice;

import com.work.skill.sync.entity.job.Job;

public interface JobSkillService {
    public void addJobSkill(Job user, String st);
    void removeAllSkillsByJobId(Long jobId);
}
