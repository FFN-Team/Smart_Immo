package com.gangdestrois.smartimmo.domain.event.model;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.enums.Status;

public class Event<T extends Model> implements Model {
    private Long id;
    private final Status status;
    private final String message;
    private final Priority priority;
    private final T element;
    private final EventType eventType;

    public Event(Status status, String message, Priority priority, T element, EventType eventType) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.element = element;
        this.eventType = eventType;
    }

    public Event(Long id, Status status, String message, Priority priority, T element, EventType eventType) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.element = element;
        this.eventType = eventType;
    }

    public Status status() {
        return this.status;
    }

    public String message() {
        return this.message;
    }

    public Priority priority() {
        return this.priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public T getElement() {
        return this.element;
    }

    public EventType getEventType() {
        return eventType;
    }

    @Override
    public Long id() {
        return id;
    }
}
