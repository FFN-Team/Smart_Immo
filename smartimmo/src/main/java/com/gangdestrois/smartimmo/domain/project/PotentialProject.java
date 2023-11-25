package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.notification.Priority;
import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;
import com.gangdestrois.smartimmo.domain.notification.State;

import java.time.LocalDate;

public record PotentialProject(Long id, LocalDate dueDate, String message, Priority priority) {
    public ProjectNotification mapToEvent() {
        return new ProjectNotification(this, State.TO_READ, message, priority);
    }
}
