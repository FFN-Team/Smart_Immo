package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;

public class Event<T extends Model> {
    private Integer id;
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

    public State state() {
        return this.state;
    }

    public String message() {
        return this.message;
    }

    public Priority priority() {
        return this.priority;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public T getElement() {
        return this.element;
    }
}
