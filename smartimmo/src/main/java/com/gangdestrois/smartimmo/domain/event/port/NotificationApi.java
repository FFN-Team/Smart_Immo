package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.NotificationStatusRequest;

public interface NotificationApi {
    //Optional<Event<? extends Notify>> findNotificationById(Long id);
    EventResponse save(Long notificationId, NotificationStatusRequest notificationStatusRequest);
}
