package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;

public class Event<T extends Model> {
    private Long id;
    private final NotificationStatus notificationStatus;
    private final String message;
    private final Priority priority;
    private final T element;
    private final EventType eventType;

    public Event(NotificationStatus notificationStatus, String message, Priority priority, T element, EventType eventType) {
        this.notificationStatus = notificationStatus;
        this.message = message;
        this.priority = priority;
        this.element = element;
        this.eventType = eventType;
    }

    public Event(Long id, NotificationStatus notificationStatus, String message, Priority priority, T element, EventType eventType) {
        this.id = id;
        this.notificationStatus = notificationStatus;
        this.message = message;
        this.priority = priority;
        this.element = element;
        this.eventType = eventType;
    }

    public NotificationStatus status() {
        return this.notificationStatus;
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
}
