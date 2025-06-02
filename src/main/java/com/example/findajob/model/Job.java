package com.example.findajob.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column
    private String type; // estágio ou jovem aprendiz

    @Column
    private String model; // presencial, híbrido ou remoto.

    @Column
    private String location;

    @Column
    private Date createdAt = new Date();

    @Column
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "enterprise_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("jobs")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "job")
    private List<Application> applications = new ArrayList<>();

    public Job() {
    }

    public Job(Long id, String title, String description, String type, String model, String location, Date createdAt, Date deadline, Enterprise enterprise, List<Application> applications, List<User> applicants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.model = model;
        this.location = location;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.enterprise = enterprise;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
