package com.example.findajob.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity
public class Academic extends User{
    @Column
    private String course;
    @Column
    private String educationalInstitution;
    @Column
    private String period;
    @Column
    private String turn;
    @Column
    private Date conclusion;

    public Academic() {
    }

    public Academic(long id, String name, String email, String password, String cpf, Date dateOfBirth, String gender, String race, Boolean pwd, String disability, String cellphone, String adress, List<Application> applications, String course, String educationalInstitution, String period, String turn, Date conclusion) {
        super(id, name, email, password, cpf, dateOfBirth, gender, race, pwd, disability, cellphone, adress, applications);
        this.course = course;
        this.educationalInstitution = educationalInstitution;
        this.period = period;
        this.turn = turn;
        this.conclusion = conclusion;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEducationalInstitution() {
        return educationalInstitution;
    }

    public void setEducationalInstitution(String educationalInstitution) {
        this.educationalInstitution = educationalInstitution;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Date getConclusion() {
        return conclusion;
    }

    public void setConclusion(Date conclusion) {
        this.conclusion = conclusion;
    }
}
