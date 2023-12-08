package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@DomainComponent
public class EventManager {
    private final Map<EventType, List<EventListener>> listeners;
    private final SubscriptionSpi subscriptionSpi;

    public EventManager(SubscriptionSpi subscriptionSpi) {
        this.subscriptionSpi = subscriptionSpi;
        listeners = this.subscriptionSpi.findAll();
    }

    public Set<Event> eventsFromEventType(EventType... eventTypes) {
        Set<Event> events = new HashSet<>();
        for (EventType eventType : eventTypes) {
            for (EventListener eventListener : listeners.get(eventType)) {
                events.addAll(eventListener.eventsFromEventType(eventTypes));
            }
        }
        return events;
    }

    public void subscribe(EventType eventType, EventListener listener) {
        if (!listeners.get(eventType).contains(listener)) {
            listeners.get(eventType).add(listener);
            subscriptionSpi.saveAll(listeners);
        }
    }

    public void unSubscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
        subscriptionSpi.saveAll(listeners);
    }

    public void notify(EventType eventType, Event event) {
        listeners.get(eventType).forEach(eventListener -> eventListener.update(eventType, event));
    }
}
