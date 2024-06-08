package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventListenerEnum;

import java.util.List;
import java.util.Map;

public interface SubscriptionSpi {
    Map<EventType, List<EventListener>> findAll();

    void saveAll(Map<EventType, List<EventListener>> listeners);

    void save(EventType eventType, EventListener listener);

    List<Long> remove(EventType eventType, EventListener listener);

    List<EventListenerEnum> findEventListenersByEventType(EventType eventType);
}
