package com.gangdestrois.smartimmo.domain.event.port;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.model.Notify;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface EventTypeNotificationSpi {
    Map<EventType, Set<Event<? extends Notify>>> findEventsGroupByEventType();

    void saveAll(Map<EventType, Set<Event<? extends Notify>>> notifications);

    void save(Event<? extends Notify> notification);

    List<Event<Notify>> findNotificationByEventType(EventType eventType);

}
