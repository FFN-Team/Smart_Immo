package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;

@DomainComponent
public class NotificationManager implements NotificationApi {
    private final NotificationSpi notificationSpi;

    public NotificationManager(NotificationSpi notificationSpi) {
        this.notificationSpi = notificationSpi;
    }

    @Override
    public EventResponse save(Long notificationId, NotificationStatusRequest notificationStatusRequest) {
        Event<Notify> originalEvent = notificationSpi.findNotificationById(notificationId)
                .orElseThrow(() -> new NotFoundException(notificationId, "notification"));
        Event<Notify> eventToSave = new Event<Notify>(
            notificationId,
            notificationStatusRequest.status(),
            originalEvent.message(),
            originalEvent.priority(),
            originalEvent.getElement(),
            originalEvent.getEventType()
        );
        Event<Notify> savedEvent = notificationSpi.save(eventToSave);
        return EventResponse.fromModel(savedEvent);
    }
}
