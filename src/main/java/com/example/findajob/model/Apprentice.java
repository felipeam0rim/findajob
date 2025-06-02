package com.example.findajob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity
public class Apprentice extends User{
    @Column
    private String educationalLevel;
    @Column
    private String disponibility;

    public Apprentice() {
    }

    public Apprentice(long id, String name, String email, String password, String cpf, Date dateOfBirth, String gender, String race, Boolean pwd, String disability, String cellphone, String adress, List<Application> applications, String educationalLevel, String disponibility) {
        super(id, name, email, password, cpf, dateOfBirth, gender, race, pwd, disability, cellphone, adress, applications);
        this.educationalLevel = educationalLevel;
        this.disponibility = disponibility;
    }

    public String getEducationalLevel() {
        return educationalLevel;
    }

    public void setEducationalLevel(String educationalLevel) {
        this.educationalLevel = educationalLevel;
    }

    public String getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
    }
}
