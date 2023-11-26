package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.notification.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class SubscriptionEntity {
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "listener")
    private String eventListener;

    public SubscriptionEntity(EventType eventType, String eventListener){
        this.eventType = eventType;
        this.eventListener = eventListener;
    }

    public EventType eventType(){
        return this.eventType;
    }

    public String eventListener(){
        return this.eventListener;
    }
}
