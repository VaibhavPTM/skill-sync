package com.work.skill.sync.repository.applicationrepo;

import com.work.skill.sync.entity.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a WHERE a.job.id = :jobId")
    List<Application> findApplicationByJobIs(@Param("jobId") Long jobId);

    @Query("SELECT a FROM Application a WHERE a.seeker.id = :seekerId")
    List<Application> findApplicationBySeekerIs(@Param("seekerId") Long seekerId);
}
