package com.example.findajob.controller;

import com.example.findajob.dto.LoginDTO;
import com.example.findajob.model.Academic;
import com.example.findajob.model.Enterprise;
import com.example.findajob.repository.AcademicRepository;
import com.example.findajob.repository.EnterpriseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprises")
@CrossOrigin("*")
public class EnterpriseController {

    private final EnterpriseRepository enterpriseRepository;
    private final AcademicRepository academicRepository;

    public EnterpriseController(EnterpriseRepository enterpriseRepository, AcademicRepository academicRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.academicRepository = academicRepository;
    }

    @GetMapping("/listAll")
    public List<Enterprise> getAllEnterprises() {
        return enterpriseRepository.findAll();
    }

    @PostMapping("/save")
    public Enterprise createEnterprise(@RequestBody Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Long id) {
        return enterpriseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable Long id, @RequestBody Enterprise updated) {
        return enterpriseRepository.findById(id)
                .map(ent -> {
                    ent.setCompanyName(updated.getCompanyName());
                    ent.setTradeName(updated.getTradeName());
                    // outros campos...
                    return ResponseEntity.ok(enterpriseRepository.save(ent));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Long id) {
        if (!enterpriseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        enterpriseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Enterprise enterprise = enterpriseRepository.findByEmail(loginDTO.getEmail());
        Academic academic = academicRepository.findByEmail(loginDTO.getEmail());

        if(enterprise != null) {
            if (!enterprise.getPassword().equals(loginDTO.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
            return ResponseEntity.ok().body(Map.of(
                    "tipo", "empresa",
                    "dados", enterprise
            ));
        }

        if (academic != null) {
            if (!academic.getPassword().equals(loginDTO.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
            }
            return ResponseEntity.ok().body(Map.of(
                    "tipo", "academico",
                    "dados", academic
            ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro desconhecido");
    }
}

