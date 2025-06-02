package com.example.findajob.repository;

import com.example.findajob.model.Academic;
import com.example.findajob.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Long> {
    Academic findByEmail(String email);
}
