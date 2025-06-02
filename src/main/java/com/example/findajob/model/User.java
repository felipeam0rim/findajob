package com.example.findajob.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column
    private String gender;
    @Column
    private String race;
    @Column
    private Boolean pwd;
    @Column
    private String disability;
    @Column
    private String cellphone;
    @Column
    private String adress;

    @OneToMany(mappedBy = "user")
    private List<Application> applications = new ArrayList<>();

    public User() {
    }

    public User(long id, String name, String email, String password, String cpf, Date dateOfBirth, String gender, String race, Boolean pwd, String disability, String cellphone, String adress, List<Application> applications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.race = race;
        this.pwd = pwd;
        this.disability = disability;
        this.cellphone = cellphone;
        this.adress = adress;
        this.applications = applications;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public Boolean getPwd() {
        return pwd;
    }

    public String getDisability() {
        return disability;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getAdress() {
        return adress;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setPwd(Boolean pwd) {
        this.pwd = pwd;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
