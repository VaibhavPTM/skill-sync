package com.work.skill.sync.repository.jobrepo;

import com.work.skill.sync.entity.job.JobSkill;
import com.work.skill.sync.entity.job.JobSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
    void deleteByJobId(Long jobId);
}
