package com.example.findajob.controller;

import com.example.findajob.dto.LoginDTO;
import com.example.findajob.model.Academic;
import com.example.findajob.model.Enterprise;
import com.example.findajob.model.User;
import com.example.findajob.repository.AcademicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academics")
@CrossOrigin("*")
public class AcademicController {

    private final AcademicRepository academicRepository;

    public AcademicController(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }

    @GetMapping("/listAll")
    public List<Academic> getAllAcademics() {
        return academicRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Academic> getAcademicById(@PathVariable Long id) {
        return academicRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public User createAcademic(@RequestBody Academic academic) {
        return academicRepository.save(academic);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Academic> updateAcademic(@PathVariable Long id, @RequestBody Academic updatedAcademic) {
        return academicRepository.findById(id)
                .map(academic -> {
                    academic.setName(updatedAcademic.getName());
                    academic.setEmail(updatedAcademic.getEmail());
                    // atualize os campos necessários
                    return ResponseEntity.ok(academicRepository.save(academic));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAcademic(@PathVariable Long id) {
        if (!academicRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        academicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Academic academic = academicRepository.findByEmail(loginDTO.getEmail());

        if (academic == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrada");
        }

        if (!academic.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
        return ResponseEntity.ok(academic);
    }
}
