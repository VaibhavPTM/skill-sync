package com.work.skill.sync.repository.jobrepo;

import com.work.skill.sync.entity.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE LOWER(j.status) = LOWER('Open')")
    List<Job> findAllOpenJob();

    @Query("SELECT j FROM Job j WHERE LOWER(j.status) = LOWER('Closed')")
    List<Job> findAllCloseJob();

    @Query("SELECT j FROM Job j WHERE LOWER(j.status) = LOWER(:status) AND j.endDate < :currentDate")
    List<Job> findExpiredJobsByStatus(@Param("status") String status, @Param("currentDate") String currentDate);

    @Query("SELECT j FROM Job j WHERE j.poster.id = :userId")
    List<Job> findJobByUserId(@Param("userId") Long userId);
}
