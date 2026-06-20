package com.mta.portfolio.project.service;

import com.mta.portfolio.project.dto.ProjectRequest;
import com.mta.portfolio.project.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project createProject(ProjectRequest request);

    Project updateProject(Long id, ProjectRequest request);

    void deleteProject(Long id);
}
