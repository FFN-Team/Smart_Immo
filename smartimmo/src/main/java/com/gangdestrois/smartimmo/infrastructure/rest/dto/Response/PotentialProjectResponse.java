package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.time.LocalDate;

public record PotentialProjectResponse(Long id, LocalDate dueDate, Priority priority) {
    public static PotentialProjectResponse fromModel(PotentialProject potentialProject) {
        return new PotentialProjectResponse(
                potentialProject.id(),
                potentialProject.getDueDate(),
                potentialProject.getPriority());
    }
}
