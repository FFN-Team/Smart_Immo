package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.project.Project;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectDataAdapter implements ProjectSpi {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectDataAdapter(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findProjectByPotentialProjectId(Long potentialProjectId) {
        return projectRepository.findProjectEntityByPotentialProjectId(potentialProjectId)
                .map(ProjectEntity::toModel);
    }
}
