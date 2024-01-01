package com.gangdestrois.smartimmo.domain.potentialProject.model;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.Status;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;

public class PotentialProject implements Model {
    private final Long id;
    private final LocalDate dueDate;
    private final String message;
    private final Priority priority;
    private final Prospect prospect;

    public PotentialProject(Long id, LocalDate dueDate, String message, Priority priority, Prospect prospect) {
        this.id = id;
        this.dueDate = dueDate;
        this.message = message;
        this.priority = priority;
        this.prospect = prospect;
    }

    public Event<PotentialProject> mapToEvent() {
        return new Event(Status.TO_READ, message, priority, this, EventType.PROJECT_DUE_DATE_APPROACHING);
    }

    @Override
    public Long id() {
        return this.id;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Prospect getProspect() {
        return prospect;
    }
}