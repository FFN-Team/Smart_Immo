package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "event_type_notification")
public class EventTypeNotificationEntity {

    @Id
    @Column(name = "event_type_notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @JoinColumn(name = "notification_id")
    @OneToOne(targetEntity = NotificationEntity.class)
    private NotificationEntity notification;

    public EventTypeNotificationEntity() {
    }

    public EventTypeNotificationEntity(EventType eventType, NotificationEntity notificationEntity) {
        this.eventType = eventType;
        this.notification = notificationEntity;
    }

    private void setNotification(NotificationEntity notificationEntity) {
        this.notification = notificationEntity;
    }

    public Map<EventType, Set<Event>> toModel(Map<EventType, Set<Event>> map) {
        if (map.containsKey(eventType)) map.get(eventType).add(notification.toModel());
        else {
            var events = new HashSet<Event>();
            events.add(notification.toModel());
            map.put(eventType, events);
        }
        return map;
    }

    public NotificationEntity notification() {
        return this.notification;
    }

    public EventType eventType() {
        return eventType;
    }
}
