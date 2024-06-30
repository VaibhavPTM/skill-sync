package com.work.skill.sync.controller;

import com.work.skill.sync.entity.application.Application;
import com.work.skill.sync.service.applicationservice.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    // send application to job with jobId. Sender id user with seekerId
    @PostMapping("/create/{jobId}/{seekerId}")
    public ResponseEntity<Application> createApplication(@PathVariable Long jobId, @PathVariable Long seekerId) {
        Application application = applicationService.createApplication(jobId, seekerId);
        return ResponseEntity.ok(application);
    }

    // update status
    @PutMapping("/update/{applicationId}/{status}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long applicationId, @PathVariable String status) {
        try {
            Application application = applicationService.updateApplication(applicationId, status);
            return ResponseEntity.ok(application);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // get all application
    @GetMapping
    public ResponseEntity<List<Application>> getAllApplication() {
        try {
            List<Application> applicationList = applicationService.getAllJob();
            return ResponseEntity.ok(applicationList);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // get application by id
    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }

    // get application by userid
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsByUserId(@PathVariable Long jobId) {
        List<Application> applications = applicationService.getApplicationsByUserId(jobId);
        return ResponseEntity.ok(applications);
    }

    // get application by seeker id
    @GetMapping("/seeker/{seekerId}")
    public ResponseEntity<List<Application>> getApplicationsBySeekerId(@PathVariable Long seekerId) {
        List<Application> applications = applicationService.getApplicationsBySeekerId(seekerId);
        return ResponseEntity.ok(applications);
    }
}
