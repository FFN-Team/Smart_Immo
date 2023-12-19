package com.gangdestrois.smartimmo.domain.potentialProject.model;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.State;

import java.time.LocalDate;

public class PotentialProject implements Model {
    private final Long id;
    private final LocalDate dueDate;
    private final String message;
    private final Priority priority;

    public PotentialProject(Long id, LocalDate dueDate, String message, Priority priority) {
        this.id = id;
        this.dueDate = dueDate;
        this.message = message;
        this.priority = priority;
    }

    public Event<PotentialProject> mapToEvent() {
        return new Event(State.TO_READ, message, priority, this);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public Priority getPriority() {
        return this.priority;
    }
}