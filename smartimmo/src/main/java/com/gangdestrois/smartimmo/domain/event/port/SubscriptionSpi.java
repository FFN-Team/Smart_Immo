package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventType;

import java.util.List;
import java.util.Map;

public interface SubscriptionSpi {
    Map<EventType, List<EventListener>> findAll();

    void saveAll(Map<EventType, List<EventListener>> listeners);
}