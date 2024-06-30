package com.work.skill.sync.service.applicationservice;

import com.work.skill.sync.entity.application.Application;

import java.util.List;

public interface ApplicationService {
    Application createApplication(Long jobId, Long seekerId);

    Application updateApplication(Long applicationId, String status);

    List<Application> getAllJob();

    Application getApplicationById(Long id);

    List<Application> getApplicationsByUserId(Long jobIs);

    List<Application> getApplicationsBySeekerId(Long seekerId);
}
