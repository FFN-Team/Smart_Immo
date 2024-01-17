package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.Map;
import java.util.Set;

public interface EventTypeNotificationSpi {

    Map<EventType, Set<Event<? extends Notify>>> findEventsGroupByEventType();

    void saveAll(Map<EventType, Set<Event<? extends Notify>>> notifications);
}
