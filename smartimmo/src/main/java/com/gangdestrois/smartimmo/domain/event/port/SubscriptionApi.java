package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;

public interface SubscriptionApi {
    boolean isNotificationSubscribe(EventType eventType);
}