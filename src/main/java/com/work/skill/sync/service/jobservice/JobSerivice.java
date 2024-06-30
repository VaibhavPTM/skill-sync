package com.work.skill.sync.service.jobservice;

import com.work.skill.sync.entity.job.Job;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface JobSerivice {
    Job createJob(Long userId, Map<String, Object> body);

    List<Job> findAllJobs();

    Optional<Job> getJobById(Long jobId);

    Job updateJob(Long jobId, Map<String, Object> body);

    void deleteJob(Long jobId);

    List<Job> findAllOpenJobs();

    List<Job> findAllCloseJobs();

    void updateExpiredJobs();

    List<Job> getAllJobByUserId(Long userId);
}
