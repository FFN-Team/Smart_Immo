package com.gangdestrois.smartimmo.domain.notification;

import com.gangdestrois.smartimmo.domain.project.PotentialProject;

public class ProjectNotification extends Event {
    private PotentialProject potentialProject;
    public ProjectNotification(PotentialProject potentialProject, State state, String message, Priority priority) {
        super(state, message, priority);
        this.potentialProject = potentialProject;
    }

    public PotentialProject potentialProject(){
        return this.potentialProject;
    }
}
