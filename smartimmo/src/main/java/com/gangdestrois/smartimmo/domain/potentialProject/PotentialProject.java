package com.gangdestrois.smartimmo.domain.potentialProject;

import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.ProjectNotification;
import com.gangdestrois.smartimmo.domain.event.State;

import java.time.LocalDate;

public record PotentialProject(Integer id, LocalDate dueDate, String message, Priority priority) {
    public ProjectNotification mapToEvent() {
        return new ProjectNotification(this, State.TO_READ, message, priority);
    }
}
