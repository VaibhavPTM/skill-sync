package com.work.skill.sync.service.jobservice;

import com.work.skill.sync.entity.job.Job;
import com.work.skill.sync.entity.job.JobSkill;
import com.work.skill.sync.entity.skill.Skill;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.entity.user.UserSkill;
import com.work.skill.sync.repository.jobrepo.JobSkillRepository;
import com.work.skill.sync.repository.skillrepo.SkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSkillServiceImp implements JobSkillService{

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void addJobSkill(Job job, String st) {
        String [] sk = st.split(",");
        for(String skill : sk) {
            skill = skill.trim();
            Optional<Skill> skill1 = skillRepository.findByName(skill);
            JobSkill jobSkill = new JobSkill(job, skill1.get());
            jobSkillRepository.save(jobSkill);
        }
    }

    @Transactional
    @Override
    public void removeAllSkillsByJobId(Long jobId) {
        jobSkillRepository.deleteByJobId(jobId);
    }
}
