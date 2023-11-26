package com.gangdestrois.smartimmo.domain.notification.port;

import com.gangdestrois.smartimmo.domain.notification.EventListener;
import com.gangdestrois.smartimmo.domain.notification.EventType;

import java.util.List;
import java.util.Map;

public interface SubscriptionSpi {
    Map<EventType, List<EventListener>> findAll();

    void saveAll(Map<EventType, List<EventListener>> listeners);
}
