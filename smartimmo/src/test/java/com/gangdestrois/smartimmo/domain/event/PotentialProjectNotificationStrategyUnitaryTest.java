package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.DataForUnitaryTest;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.infrastructure.jpa.NotificationDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PotentialProjectNotificationStrategyUnitaryTest {
    public NotificationSpi notificationSpi;
    public EventManager eventManager;
    public PotentialProjectNotificationStrategy potentialProjectNotificationStrategy;
    public DataForUnitaryTest dataForUnitaryTest = new DataForUnitaryTest();

    @BeforeEach
    public void setUp() {
        notificationSpi = mock(NotificationDataAdapter.class);
        eventManager = mock(EventManager.class);
        potentialProjectNotificationStrategy = new PotentialProjectNotificationStrategy(notificationSpi,
                eventManager);
    }

    @Test
    public void makeNotification_should_do_nothing_when_there_is_already_a_notification_for_the_element_to_notify() {
        // Getters
        List<PotentialProject> elementsToNotify = List.of(dataForUnitaryTest.potentialProject);
        var eventType = EventType.PROJECT_DUE_DATE_APPROACHING;
        List<Event<Notify>> events = new ArrayList<>();
        events.add(new Event<Notify>(NotificationStatus.TO_READ, "test", Priority.LOW, elementsToNotify.getFirst(),
                eventType));
        when(notificationSpi.findNotificationByElementIdAndStatusAndEventType(
                1L, NotificationStatus.statusesNotAlreadyDealt(), eventType))
                .thenReturn(events);

        // When
        potentialProjectNotificationStrategy.makeNotification(elementsToNotify, eventType);

        // Then
        verify(eventManager, never()).notify(any());
    }
}
