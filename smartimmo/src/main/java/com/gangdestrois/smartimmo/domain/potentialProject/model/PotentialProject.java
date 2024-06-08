package com.gangdestrois.smartimmo.domain.potentialProject.model;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.model.Notify;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;

public class PotentialProject implements Notify {
    private Long id;
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
        return new Event(NotificationStatus.TO_READ, message, priority, this, EventType.PROJECT_DUE_DATE_APPROACHING);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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