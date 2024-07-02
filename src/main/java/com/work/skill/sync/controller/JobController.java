package com.work.skill.sync.controller;

import com.work.skill.sync.entity.job.Job;
import com.work.skill.sync.service.jobservice.JobSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @Autowired
    private JobSerivice jobSerivice;

    // create a job
    @PostMapping("{userId}/create-job")
    public ResponseEntity<Job> createJob(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        try {
            Job job = jobSerivice.createJob(userId, body);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            Job job = new Job();
            job.setDescription(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(job);
        }
    }

    // update job by job id
    @PutMapping("/updatejob/{jobId}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long jobId, @RequestBody Map<String, Object> body) {
        try {
            Job job = jobSerivice.updateJob(jobId, body);
            return ResponseEntity.status(HttpStatus.OK).body(job);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // delete job by jobId
    @DeleteMapping("deletejob/{jobId}")
    public ResponseEntity<Map<String, String>> deleteJob(@PathVariable Long jobId) {
        jobSerivice.deleteJob(jobId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Job successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // get all job
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        try{
            List<Job> jobs = jobSerivice.findAllJobs();
            return ResponseEntity.ok(jobs);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/open")
    public ResponseEntity<List<Job>> getAllOpenJobs() {
        try{
            List<Job> jobs = jobSerivice.findAllOpenJobs();
            return ResponseEntity.ok(jobs);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/close")
    public ResponseEntity<List<Job>> getAllCloseJobs() {
        try{
            List<Job> jobs = jobSerivice.findAllCloseJobs();
            return ResponseEntity.ok(jobs);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Job>> getAllJobByUserId(@PathVariable Long userId) {
        try {
            List<Job> jobs = jobSerivice.getAllJobByUserId(userId);
            return ResponseEntity.ok(jobs);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // get job by JobId
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        Optional<Job> job = jobSerivice.getJobById(jobId);
        return job.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateExpiredJobs")
    public ResponseEntity<Map<String, String>> updateExpiredJobs() {
        jobSerivice.updateExpiredJobs();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Expired jobs updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("search/{skillId}")
    ResponseEntity<List<Job>> searchJobSkillId(@PathVariable Long skillId) {
        try {
            List<Job> jobList = jobSerivice.searchJobBySkillId(skillId);
            return ResponseEntity.ok(jobList);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @GetMapping("/test")
    public String testing() {
        return "This is working!!!";
    }
}
