package com.work.skill.sync.service.applicationservice;

import com.work.skill.sync.entity.application.Application;
import com.work.skill.sync.entity.job.Job;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.repository.applicationrepo.ApplicationRepository;
import com.work.skill.sync.repository.jobrepo.JobRepository;
import com.work.skill.sync.repository.userrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImp implements ApplicationService{

    @Autowired
    JobRepository jobRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public Application createApplication(Long jobId, Long seekerId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        User seeker = userRepository.findById(seekerId).orElseThrow(() -> new RuntimeException("Seeker not found"));
        Application application = new Application();
        application.setJob(job);
        application.setSeeker(seeker);
        application.setStatus("pending");
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getAllJob() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));

    }

    @Override
    public List<Application> getApplicationsByUserId(Long jobId) {
        return applicationRepository.findApplicationByJobIs(jobId);
    }

    @Override
    public List<Application> getApplicationsBySeekerId(Long seekerId) {
        return applicationRepository.findApplicationBySeekerIs(seekerId);
    }
}
