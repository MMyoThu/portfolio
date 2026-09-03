package com.mta.portfolio.project.service.impl;

import com.mta.portfolio.common.exception.ResourceNotFoundException;
import com.mta.portfolio.project.dto.ProjectRequest;
import com.mta.portfolio.project.entity.Project;
import com.mta.portfolio.project.repository.ProjectRepository;
import com.mta.portfolio.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Project getProjectById(Long id) {
        return findProject(id);
    }

    @Override
    public Project createProject(ProjectRequest request) {
        Project project = new Project();
        applyRequest(project, request);
        project.setFeatured(Boolean.TRUE.equals(request.getFeatured()));
        return repository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectRequest request) {
        Project project = findProject(id);
        applyRequest(project, request);
        if (request.getFeatured() != null) {
            project.setFeatured(request.getFeatured());
        }
        return repository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Project", "id", id);
        }
        repository.deleteById(id);
    }

    private Project findProject(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
    }

    private void applyRequest(Project project, ProjectRequest request) {
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setTechnologyStack(request.getTechnologyStack());
        project.setImageUrl(request.getImageUrl());
    }
}
