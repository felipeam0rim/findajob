package com.example.findajob.controller;

import com.example.findajob.model.Enterprise;
import com.example.findajob.model.Job;
import com.example.findajob.repository.EnterpriseRepository;
import com.example.findajob.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    private final JobRepository jobRepository;
    private final EnterpriseRepository enterpriseRepository;

    public JobController(JobRepository jobRepository, EnterpriseRepository enterpriseRepository) {
        this.jobRepository = jobRepository;
        this.enterpriseRepository = enterpriseRepository;
    }

    @GetMapping("/listAll")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save/{enterpriseId}")
    public ResponseEntity<Job> createJob(@PathVariable Long enterpriseId, @RequestBody Job job) {
        return enterpriseRepository.findById(enterpriseId)
                .map(enterprise -> {
                    job.setEnterprise(enterprise);
                    return ResponseEntity.ok(jobRepository.save(job));
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updated) {
        return jobRepository.findById(id)
                .map(ent -> {
                    ent.setDeadline(updated.getDeadline());
                    ent.setDescription(updated.getDescription());
                    ent.setModel(updated.getModel());
                    ent.setType(updated.getType());
                    ent.setTitle(updated.getTitle());
                    // outros campos...
                    return ResponseEntity.ok(jobRepository.save(ent));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        if (!jobRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byEnterprise/{enterpriseId}")
    public List<Job> getJobsByEnterprise(@PathVariable Long enterpriseId) {
        return jobRepository.findByEnterpriseId(enterpriseId);
    }

}

