package com.mta.portfolio.project.service.impl;

import com.mta.portfolio.common.exception.ResourceNotFoundException;
import com.mta.portfolio.project.dto.ProjectRequest;
import com.mta.portfolio.project.entity.Project;
import com.mta.portfolio.project.repository.ProjectRepository;
import com.mta.portfolio.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    }

    @Override
    public Project createProject(ProjectRequest request) {
        Project project = new Project();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setTechnologyStack(request.getTechnologyStack());
        project.setImageUrl(request.getImageUrl());
        project.setFeatured(request.getFeatured() != null ? request.getFeatured() : false);
        return repository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectRequest request) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setTechnologyStack(request.getTechnologyStack());
        project.setImageUrl(request.getImageUrl());
        project.setFeatured(request.getFeatured() != null ? request.getFeatured() : project.getFeatured());
        return repository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        repository.delete(project);
    }
}
