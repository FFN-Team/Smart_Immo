package com.gangdestrois.smartimmo.domain.project.port;

import com.gangdestrois.smartimmo.domain.project.model.Project;

import java.util.Optional;

public interface ProjectSpi {
    Optional<Project> findProjectByPotentialProjectId(Long potentialProjectId);
}
