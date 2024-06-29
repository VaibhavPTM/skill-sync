package com.work.skill.sync.service.userservice;

import com.work.skill.sync.entity.skill.Skill;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.entity.user.UserSkill;
import com.work.skill.sync.repository.skillrepo.SkillRepository;
import com.work.skill.sync.repository.userrepo.UserSkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSkillServiceImp implements UserSkillService {

    @Autowired
    public UserSkillRepository userSkillRepository;

    @Autowired
    public SkillRepository skillRepository;

    @Override
    public void addUserSkill(User user, String st) {
        String[] skillsArray = st.split(",");
        for(String skll : skillsArray) {
            skll = skll.trim();
            Optional<Skill> skill = skillRepository.findByName(skll);
            UserSkill userSkill = new UserSkill(user, skill.get());
            userSkillRepository.save(userSkill);
        }
    }

    @Transactional
    @Override
    public void removeAllSkillsByUserId(Long userId) {
        userSkillRepository.deleteByUserId(userId);
    }
}
