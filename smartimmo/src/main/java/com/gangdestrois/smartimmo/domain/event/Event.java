package com.gangdestrois.smartimmo.domain.event;

public class Event {
    private Integer id;
    private final State state;
    private final String message;
    private final Priority priority;

    public Event(State state, String message, Priority priority) {
        this.state = state;
        this.message = message;
        this.priority = priority;
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
}
