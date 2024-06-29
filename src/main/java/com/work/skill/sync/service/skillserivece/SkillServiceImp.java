package com.work.skill.sync.service.skillserivece;

import com.work.skill.sync.entity.skill.Skill;
import com.work.skill.sync.repository.skillrepo.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImp implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public void addSkills(String skillString) {
        String[] skillsArray = skillString.split(",");
        for(String skillName : skillsArray) {
            skillName = skillName.trim();
            if (!skillRepository.findByName(skillName).isPresent()) {
                Skill skill = new Skill();
                skill.setName(skillName);
                skillRepository.save(skill);
            }
        }

    }
}