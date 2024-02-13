package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.EventTypeNotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.NotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.SubscriptionDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class EventManagerTest {

    private EventManager eventService;
    private SubscriptionSpi subscriptionSpi;

    @Before
    public void setUp() {
        // Initialisation des objets nécessaires
        subscriptionSpi = mock(SubscriptionDataAdapter.class);
        EventTypeNotificationSpi eventTypeNotificationSpi = mock(EventTypeNotificationDataAdapter.class);
        NotificationSpi notificationSpi = mock(NotificationDataAdapter.class);
        eventService = new EventManager(subscriptionSpi, eventTypeNotificationSpi, notificationSpi); // Assurez-vous que le constructeur prend la dépendance en paramètre
    }

    @Test
    public void unsubscribe_should_remove_entry_set_when_eventType_subscribe_event_listener() {
        // Getters
        EventType eventType = EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE;
        EventListener listener = mock(EventListener.class);
        when(subscriptionSpi.findAll()).thenReturn(Map.of(EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE,
                List.of(mock(EventListener.class))));

        // When
        eventService.unSubscribe(eventType, listener);

        // Then
        verify(subscriptionSpi).remove(eq(eventType), eq(listener));
    }

    @Test
    public void unsubscribe_should_throw_bad_request_exception_when_eventType_subscribe_no_event_listener(){
        // Getters
        EventType eventType = EventType.PROJECT_DUE_DATE_APPROACHING;
        EventListener listener = mock(EventListener.class);
        when(subscriptionSpi.findAll()).thenReturn(Map.of(EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE,
                List.of(mock(EventListener.class))));

        // When and then
        assertThrows(BadRequestException.class, () -> eventService.unSubscribe(eventType, listener));
    }
}
