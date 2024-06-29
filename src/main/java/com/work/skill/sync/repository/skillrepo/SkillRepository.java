package com.work.skill.sync.repository.skillrepo;

import com.work.skill.sync.entity.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);

    @Query("SELECT s.name FROM UserSkill us JOIN us.skill s WHERE us.user.id = :userId")
    List<String> findSkillsByUserId(@Param("userId") Long userId);


}
