package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventListenerEnum;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Override
    List<SubscriptionEntity> findAll();

    List<SubscriptionEntity> findAllByEventListenerAndAndEventType(EventListenerEnum eventListenerEnum, EventType eventType);
}
