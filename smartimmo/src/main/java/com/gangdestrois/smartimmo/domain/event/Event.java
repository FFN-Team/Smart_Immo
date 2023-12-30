package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;

public class Event<T extends Model> {
    private Long id;
    private final Status status;
    private final String message;
    private final Priority priority;
    private final T element;

    public Event(Status status, String message, Priority priority, T element) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.element = element;
    }

    public Event(Long id, Status status, String message, Priority priority, T element) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.element = element;
    }

    public Event(Long id, Status status) {
        this.id = id;
        this.status = status;
        this.message = null;
        this.priority = null;
        this.element = null;
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
}
