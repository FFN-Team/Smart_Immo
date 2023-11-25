package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.bien.port.BienPort;
import com.gangdestrois.smartimmo.domain.notification.EventListener;
import com.gangdestrois.smartimmo.domain.notification.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.SubscriptionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionDataAdapter implements BienPort {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDataAdapter(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public Map<EventType, List<EventListener>> findAll() {
        Map<EventType, List<EventListener>> map = new HashMap<>();
        subscriptionRepository.findAll()
                .forEach(subscriptionEntity -> subscriptionEntity. (map))
        return map;
    }
}
