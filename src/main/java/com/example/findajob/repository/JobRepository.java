package com.example.findajob.repository;

import com.example.findajob.model.Enterprise;
import com.example.findajob.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEnterpriseId(Long id);
}