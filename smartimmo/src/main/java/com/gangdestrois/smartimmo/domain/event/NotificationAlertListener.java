package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;

@DomainComponent
public class NotificationAlertListener implements EventListener {
    private final EventTypeNotificationSpi eventTypeNotificationSpi;
    private final NotificationSpi notificationSpi;

    public NotificationAlertListener(EventTypeNotificationSpi eventTypeNotificationSpi, NotificationSpi notificationSpi) {
        this.eventTypeNotificationSpi = eventTypeNotificationSpi;
        this.notificationSpi = notificationSpi;
    }

    @Override
    public void update(Event<? extends Notify> notification) {
        notification.setId(notificationSpi.saveNotification(notification));
        eventTypeNotificationSpi.save(notification);
    }
}
