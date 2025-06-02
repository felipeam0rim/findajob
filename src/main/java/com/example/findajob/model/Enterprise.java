package com.example.findajob.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String tradeName;
    @Column(nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String lineOfBusiness;
    @Column
    private Integer numberOfEmployees;
    @Column
    private String vacancyPreference;
    @Column
    private String companySize;
    @Column
    private String businessAddress;

    @OneToMany(mappedBy = "enterprise")
    private List<Job> jobs = new ArrayList<>();

    public Enterprise() {
    }

    public Enterprise(long id, String companyName, String tradeName, String cnpj, String email, String lineOfBusiness, Integer numberOfEmployees, String vacancyPreference, String companySize, String businessAddress, List<Job> jobs) {
        this.id = id;
        this.companyName = companyName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.email = email;
        this.lineOfBusiness = lineOfBusiness;
        this.numberOfEmployees = numberOfEmployees;
        this.vacancyPreference = vacancyPreference;
        this.companySize = companySize;
        this.businessAddress = businessAddress;
        this.jobs = jobs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public Integer getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Integer numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getVacancyPreference() {
        return vacancyPreference;
    }

    public void setVacancyPreference(String vacancyPreference) {
        this.vacancyPreference = vacancyPreference;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
