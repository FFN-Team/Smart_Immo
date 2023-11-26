package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.SubscriptionEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionDataAdapter implements SubscriptionSpi {
    private final SubscriptionRepository subscriptionRepository;
    private final NotificationAlertListener notificationAlertListener;

    public SubscriptionDataAdapter(SubscriptionRepository subscriptionRepository,
                                   NotificationAlertListener notificationAlertListener) {
        this.subscriptionRepository = subscriptionRepository;
        this.notificationAlertListener = notificationAlertListener;
    }

    @Override
    public Map<EventType, List<EventListener>> findAll() {
        Map<EventType, List<EventListener>> listeners = new HashMap<>();
        for (EventType eventType : EventType.values()) {
            listeners.put(eventType, new ArrayList<>());
        }
        subscriptionRepository.findAll()
                .forEach(subscriptionEntity ->
                        listeners.get(subscriptionEntity.eventType())
                                .add(mapEventListenerFromDBData(subscriptionEntity.eventListener())));
        return listeners;
    }

    @Transactional
    public void saveAll(Map<EventType, List<EventListener>> listeners) {
        List<SubscriptionEntity> subscriptions = new ArrayList<>();
        listeners.forEach((eventType, eventListeners) -> eventListeners
                .forEach(eventListener ->
                        subscriptions.add(new SubscriptionEntity(eventType, mapEventListenerToDBData(eventListener)))));
        subscriptionRepository.saveAll(subscriptions);
    }

    //WARNING : SWITCH STATEMENT, TO REVIEW
    private EventListener mapEventListenerFromDBData(String eventListener) {
        if (eventListener.equals("notificationAlertListener")) {
            return this.notificationAlertListener;
        }
        throw new RuntimeException("l'event listener en bd ne correspond à aucun event listener.");
    }

    private String mapEventListenerToDBData(EventListener eventListener) {
        if (eventListener instanceof NotificationAlertListener) {
            return "notificationAlertListener";
        }
        throw new RuntimeException("l'event listener en bd ne correspond à aucun event listener.");
    }
}
