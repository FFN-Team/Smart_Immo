package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.EventType;
import jakarta.persistence.*;

@Entity
@Table(name = "subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "event_listener")
    private String eventListener;

    public SubscriptionEntity(EventType eventType, String eventListener) {
        this.eventType = eventType;
        this.eventListener = eventListener;
    }

    public SubscriptionEntity() {
    }

    public EventType eventType() {
        return this.eventType;
    }

    public String eventListener() {
        return this.eventListener;
    }
}
