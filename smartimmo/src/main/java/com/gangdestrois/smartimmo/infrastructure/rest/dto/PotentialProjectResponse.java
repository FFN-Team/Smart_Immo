package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProject;

public record PotentialProjectResponse() {
    public PotentialProjectResponse fromModel(PotentialProject potentialProject) {
        return new PotentialProjectResponse();
    }
}
