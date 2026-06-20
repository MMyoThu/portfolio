package com.mta.portfolio.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {
    @NotBlank
    private String title;
    private String description;
    private String githubUrl;
    private String demoUrl;
    private String technologyStack;
    private String imageUrl;
    private Boolean featured;
}
