package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.EventListenerEnum;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    List<SubscriptionEntity> findAllByEventListenerAndAndEventType(EventListenerEnum eventListenerEnum, EventType eventType);

    @Query(value = """
            select s.eventListener from SubscriptionEntity s where s.eventType = :eventType
            """)
    List<EventListenerEnum> findAllEventListenerByEventType(EventType eventType);
}
