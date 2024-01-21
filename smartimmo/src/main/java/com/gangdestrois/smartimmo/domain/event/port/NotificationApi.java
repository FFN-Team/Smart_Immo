package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;

public interface NotificationApi {
    EventResponse save(Long notificationId, NotificationStatusRequest notificationStatusRequest);
}
