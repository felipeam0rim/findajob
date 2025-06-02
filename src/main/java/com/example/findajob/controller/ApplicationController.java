package com.example.findajob.controller;

import com.example.findajob.model.Application;
import com.example.findajob.model.Job;
import com.example.findajob.model.User;
import com.example.findajob.repository.ApplicationRepository;
import com.example.findajob.repository.JobRepository;
import com.example.findajob.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationController(ApplicationRepository applicationRepository, UserRepository userRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<Application> applyToJob(@RequestParam Long userId, @RequestParam Long jobId) {
        Optional<Application> existing = applicationRepository.findByUserIdAndJobId(userId, jobId);
        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // já aplicado
        }

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Job> jobOpt = jobRepository.findById(jobId);

        if (userOpt.isPresent() && jobOpt.isPresent()) {
            Application app = new Application();
            app.setUser(userOpt.get());
            app.setJob(jobOpt.get());
            app.setApplicationDate(new Date());
            app.setStatus("PENDENTE");
            return ResponseEntity.ok(applicationRepository.save(app));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUser(@PathVariable Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getApplicationsByJob(@PathVariable Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return applicationRepository.findById(id)
                .map(app -> {
                    app.setStatus(status);
                    return ResponseEntity.ok(applicationRepository.save(app));
                }).orElse(ResponseEntity.notFound().build());
    }
}

