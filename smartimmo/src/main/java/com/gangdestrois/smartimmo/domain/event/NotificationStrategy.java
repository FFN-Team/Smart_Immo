package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.Optional;

public interface NotificationStrategy<T extends Notify> {
    Long save(Event<T> event);

    Optional<Event<T>> findNotificationById(Long notificationId);
}
