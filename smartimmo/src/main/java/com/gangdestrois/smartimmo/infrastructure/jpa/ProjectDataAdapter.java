package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.project.Project;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProjectRepository;

import java.util.Optional;

public class ProjectDataAdapter implements ProjectSpi {
    private final ProjectRepository projectRepository;

    public ProjectDataAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findProjectByPotentialProjectId(Long potentialProjectId) {
        return projectRepository.findProjectEntityByPotentialProjectId(potentialProjectId)
                .map(ProjectEntity::toModel);
    }
}
