package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "event_type_notification")
public class EventTypeNotificationEntity {
    @Id
    @Column(name = "id_event_type_notification")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @JoinColumn(name = "fk_notification", referencedColumnName = "id_notification")
    @ManyToOne(targetEntity = NotificationEntity.class)
    private NotificationEntity notification;

    public EventTypeNotificationEntity() {
    }

    public EventTypeNotificationEntity(EventType eventType, NotificationEntity notificationEntity) {
        this.eventType = eventType;
        this.notification = notificationEntity;
    }

    public Map<EventType, Set<Event<Notify>>> toModel(Map<EventType, Set<Event<Notify>>> map) {
        if (map.containsKey(eventType)) map.get(eventType).add(notification.toModel());
        else {
            var events = new HashSet<Event<Notify>>();
            events.add(notification.toModel());
            map.put(eventType, events);
        }
        return map;
    }
}
