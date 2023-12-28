package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.time.LocalDate;

public record PotentialProjectResponse(Long id, LocalDate dueDate, Priority priority) {
    public static PotentialProjectResponse fromModel(PotentialProject potentialProject) {
        return new PotentialProjectResponse(
                potentialProject.getId(),
                potentialProject.getDueDate(),
                potentialProject.getPriority());
    }
}
