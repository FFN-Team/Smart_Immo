package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.jpa.NotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.SubscriptionDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NotificationDataAdapter.class, SubscriptionDataAdapter.class, ProspectResponse.class,
        Prospect.class, EventResponse.class})
public class EventManagerTest {
    private EventManager eventManager;
    private NotificationSpi notificationSpi;

    @Before
    public void setUp() throws Exception {
        notificationSpi = mock(NotificationDataAdapter.class);
        SubscriptionSpi subscriptionSpi = mock(SubscriptionDataAdapter.class);
        eventManager = new EventManager(subscriptionSpi, notificationSpi);
    }

    @Test
    public void saveShouldThrowNotFoundExceptionWhenNotificationIdNotFound() {
        Long notificationId = -1L;
        NotificationStatusRequest notificationStatusRequest = new NotificationStatusRequest(NotificationStatus.OPEN);
        when(notificationSpi.findNotificationById(notificationId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> eventManager.save(notificationId, notificationStatusRequest));
    }

    @Test
    public void saveShouldReturnSavedNotificationWhenNotificationIdFound() {
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

        EventResponse actual = eventManager.save(notificationId, notificationStatusRequest);

        assertEquals(expected, actual);
        verify(notificationSpi).findNotificationById(notificationId);
        verify(notificationSpi).save(any());
        PowerMockito.verifyStatic(EventResponse.class);
        EventResponse.fromModel(any());
    }
}