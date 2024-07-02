package com.work.skill.sync.service.jobservice;

import com.work.skill.sync.entity.exception.ResourceNotFoundException;
import com.work.skill.sync.entity.job.Job;
import com.work.skill.sync.entity.user.User;
import com.work.skill.sync.repository.jobrepo.JobRepository;
import com.work.skill.sync.repository.userrepo.UserRepository;
import com.work.skill.sync.service.skillserivece.SkillService;
import com.work.skill.sync.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobServiceImp implements JobSerivice{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    private JobSkillService jobSkillService;

    @Override
    public Job createJob(Long userId, Map<String, Object> body) {
        try {
            if(userRepository.findById(userId).isPresent()) {
                Job job = new Job();
                job.setTitle(body.get("title").toString());
                job.setDescription(body.get("description").toString());
                job.setLocation(body.get("location").toString());
                job.setLongitude(Double.valueOf(body.get("longitude").toString()));
                job.setLatitude(Double.valueOf(body.get("latitude").toString()));
                job.setDressCode(body.get("dressCode").toString());
                job.setPayment(Double.valueOf(body.get("payment").toString()));
                job.setStartDate(body.get("startDate").toString());
                job.setEndDate(body.get("endDate").toString());
                job.setStatus(body.get("status").toString());
                job.setPoster(userRepository.findById(userId).get());
                job.setSk(body.get("sk").toString());
                {
                    jobRepository.save(job);
                    skillService.addSkills(job.getSk());
                    jobSkillService.addJobSkill(job, job.getSk());
                }
                return job;
            }
            else {
                throw new RuntimeException("User with userId " + userId + " dose not exist");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }
    @Override
    public Job updateJob(Long jobId, Map<String, Object> body) {
        if(jobRepository.findById(jobId).isPresent()) {
            Job job = jobRepository.findById(jobId).get();
            job.setTitle(body.get("title").toString());
            job.setDescription(body.get("description").toString());
            job.setLocation(body.get("location").toString());
            job.setLongitude(Double.valueOf(body.get("longitude").toString()));
            job.setLatitude(Double.valueOf(body.get("latitude").toString()));
            job.setDressCode(body.get("dressCode").toString());
            job.setPayment(Double.valueOf(body.get("payment").toString()));
            job.setStartDate(body.get("startDate").toString());
            job.setEndDate(body.get("endDate").toString());
            job.setStatus(body.get("status").toString());
            job.setSk(body.get("sk").toString());
            {
                // remove skills with jobId
                jobSkillService.removeAllSkillsByJobId(jobId);
                // add skills to skill table
                skillService.addSkills(job.getSk());
                // add userid and skillId to jobSkill table
                jobSkillService.addJobSkill(job, job.getSk());
            }
            return jobRepository.save(job);
        }
        else {
            throw new ResourceNotFoundException("Job not found with id " + jobId);
        }
    }

    @Override
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }

    @Override
    public List<Job> findAllOpenJobs() {
        return jobRepository.findAllOpenJob();
    }

    @Override
    public List<Job> findAllCloseJobs() {
        return jobRepository.findAllCloseJob();
    }

    @Override
    public void updateExpiredJobs() {
        LocalDate currentDate = LocalDate.now();
        List<Job> expiredJobs = jobRepository.findExpiredJobsByStatus("Open", currentDate.toString());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Job job : expiredJobs) {
            LocalDate endDate = LocalDate.parse(job.getEndDate(), formatter);

            if (endDate.isBefore(currentDate)) {
                job.setStatus("Closed");
                jobRepository.save(job);
            }
        }
    }

    @Override
    public List<Job> getAllJobByUserId(Long userId) {
        return jobRepository.findJobByUserId(userId);
    }

    @Override
    public List<Job> searchJobBySkillId(Long skillId) {
        return jobRepository.findBySkillId(skillId);
    }

    @Override
    public List<Job> findAllJobs() {
        try {
            return jobRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Something went happen");
        }
    }

    @Override
    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }


}
