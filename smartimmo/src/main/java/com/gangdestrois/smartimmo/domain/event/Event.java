package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;

public class Event<T extends Model> {
    private Long id;
    private final State state;
    private final String message;
    private final Priority priority;
    private final T element;

    public Event(State state, String message, Priority priority, T element) {
        this.state = state;
        this.message = message;
        this.priority = priority;
        this.element = element;
    }

    public Event(Long id, State state, String message, Priority priority, T element) {
        this.id = id;
        this.state = state;
        this.message = message;
        this.priority = priority;
        this.element = element;
    }

    public Event(Long id, State state) {
        this.id = id;
        this.state = state;
        this.message = null;
        this.priority = null;
        this.element = null;
    }

    public State state() {
        return this.state;
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
