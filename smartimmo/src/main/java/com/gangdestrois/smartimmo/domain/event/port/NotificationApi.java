package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.Optional;

public interface NotificationApi {
    Optional<Event<? extends Notify>> findNotificationById(Long id);

    Event save(Event event);
}
