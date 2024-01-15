package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.List;
import java.util.Optional;

public interface NotificationStrategy<T extends Notify> {
    Long save(Event<T> event);

    Optional<Event<T>> findNotificationById(Long notificationId);

    void makeNotification(List<T> element, EventType eventType);

    List<Event<T>> getNotifications(EventType eventType);

    Event<T> saveNotification(T potentialProject);

}
