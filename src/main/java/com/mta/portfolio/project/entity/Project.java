package com.mta.portfolio.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String githubUrl;
    private String demoUrl;
    private String technologyStack;
    private String imageUrl;
    private Boolean featured;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
