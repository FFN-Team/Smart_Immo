package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionApi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventListenerEnum;

public class SubscriptionManager implements SubscriptionApi {
    private final SubscriptionSpi subscriptionSpi;

    public SubscriptionManager(SubscriptionSpi subscriptionSpi) {
        this.subscriptionSpi = subscriptionSpi;
    }

    @Override
    public boolean isNotificationSubscribe(EventType eventType) {
        return subscriptionSpi.findEventListenersByEventType(eventType).stream()
                .anyMatch(eventListener -> eventListener.equals(EventListenerEnum.NOTIFICATION_ALERT_LISTENER));
    }
}