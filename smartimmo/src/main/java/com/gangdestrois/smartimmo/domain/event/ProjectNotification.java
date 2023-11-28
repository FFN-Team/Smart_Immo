package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProject;

public class ProjectNotification extends Event {
    private final PotentialProject potentialProject;

    public ProjectNotification(PotentialProject potentialProject, State state, String message, Priority priority) {
        super(state, message, priority);
        this.potentialProject = potentialProject;
    }

    public PotentialProject potentialProject() {
        return this.potentialProject;
    }
}
