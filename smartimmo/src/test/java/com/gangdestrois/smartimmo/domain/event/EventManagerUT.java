package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.EventTypeNotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.jpa.EventTypeNotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.NotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.SubscriptionDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NotificationDataAdapter.class, SubscriptionDataAdapter.class, ProspectResponse.class,
        Prospect.class, EventResponse.class})
public class EventManagerUT {

    private EventManager eventService;
    private SubscriptionSpi subscriptionSpi;
    private NotificationSpi notificationSpi;

    @Before
    public void setUp() {
        subscriptionSpi = mock(SubscriptionDataAdapter.class);
        EventTypeNotificationSpi eventTypeNotificationSpi = mock(EventTypeNotificationDataAdapter.class);
        notificationSpi = mock(NotificationDataAdapter.class);
        eventService = new EventManager(subscriptionSpi, eventTypeNotificationSpi, notificationSpi);
    }

    @Test
    public void unsubscribe_should_throw_bad_request_exception_when_eventType_subscribe_no_event_listener() {
        // Getters
        EventType eventType = EventType.PROJECT_DUE_DATE_APPROACHING;
        EventListener listener = mock(EventListener.class);
        when(subscriptionSpi.findAll()).thenReturn(Map.of(EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE,
                List.of(mock(EventListener.class))));

        // When and then
        assertThrows(BadRequestException.class, () -> eventService.unSubscribe(eventType, listener));
    }

    @Test
    public void unsubscribe_should_remove_entry_set_when_eventType_subscribe_eventListener() {
        // Getters
        EventType eventType = EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE;
        EventListener listener = mock(EventListener.class);
        when(subscriptionSpi.findAll()).thenReturn(Map.of(EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE,
                List.of(listener)));

        // When
        eventService.unSubscribe(eventType, listener);

        // Then
        verify(subscriptionSpi).remove(eq(eventType), eq(listener));
    }

/*    @Test
    public void unsubscribe_should_do_nothing_when_eventType_subscribe_another_eventLiner(){
        // Getters
        EventType eventType = EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE;
        EventListener listener = mock(NotificationAlertListener.class);
        EventListener anotherListener = mock(EventListener.class);
        when(subscriptionSpi.findAll()).thenReturn(Map.of(EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE,
                List.of(anotherListener)));

        // When
        eventService.unSubscribe(eventType, listener);

        // Then
        verify(subscriptionSpi, never()).remove(eq(eventType), eq(listener));
    }*/

    @Test
    public void save_should_throw_not_found_exception_when_notification_id_not_found() {
        // Given
        Long notificationId = -1L;
        NotificationStatusRequest notificationStatusRequest = new NotificationStatusRequest(NotificationStatus.OPEN);
        when(notificationSpi.findNotificationById(notificationId)).thenReturn(Optional.empty());

        // When and then
        assertThrows(NotFoundException.class, () -> eventService.save(notificationId, notificationStatusRequest));
    }

    @Test
    public void save_should_return_saved_notification_when_notification_id_found() {
        // Given
        Long notificationId = 1L;
        NotificationStatusRequest notificationStatusRequest = new NotificationStatusRequest(NotificationStatus.OPEN);
        ProspectResponse prospectResponse = PowerMockito.mock(ProspectResponse.class);
        Event<Notify> event = new Event<Notify>(
                notificationId,
                notificationStatusRequest.status(),
                "Message",
                Priority.LOW,
                mock(Prospect.class),
                EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE
        );
        EventResponse expected = new EventResponse(
                notificationId,
                notificationStatusRequest.status().name(),
                "Message",
                Priority.LOW.name(),
                prospectResponse
        );

        when(notificationSpi.findNotificationById(anyLong())).thenReturn(Optional.of(event));
        when(notificationSpi.save(any())).thenReturn(event);
        PowerMockito.mockStatic(EventResponse.class);
        PowerMockito.when(EventResponse.fromModel(any())).thenReturn(new EventResponse(
                        event.getId(),
                        event.status().name(),
                        event.message(),
                        event.priority().name(),
                        prospectResponse
                )
        );

        // When
        EventResponse actual = eventService.save(notificationId, notificationStatusRequest);

        // Then
        assertEquals(expected, actual);
        verify(notificationSpi).findNotificationById(notificationId);
        verify(notificationSpi).save(any());
        PowerMockito.verifyStatic(EventResponse.class);
        EventResponse.fromModel(any());
    }
}
