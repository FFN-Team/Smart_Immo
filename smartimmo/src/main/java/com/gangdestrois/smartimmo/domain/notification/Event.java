package com.gangdestrois.smartimmo.domain.notification;

public class Event {
    private State state;
    private String message;
    private Priority priority;

    public Event(State state, String message, Priority priority) {
        this.state = state;
        this.message = message;
        this.priority = priority;
    }

    public State state(){
        return this.state;
    }

    public String message(){
        return this.message;
    }

    public Priority priority(){
        return this.priority;
    }
}
