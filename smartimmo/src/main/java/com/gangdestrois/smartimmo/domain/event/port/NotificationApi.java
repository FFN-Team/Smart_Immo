package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Event;

import java.util.Optional;

public interface NotificationApi {
    Optional<Event> findNotificationById(Long id);
    Event save(Event event);
}
