package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;

import java.time.LocalDate;

public record PotentialProject(Long id, LocalDate dueDate) {
    public ProjectNotification mapToEvent() {
        return new ProjectNotification();
    }
}
