package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {
    @MockBean
    private EventManager eventManager;

    class NotificationSpiMock implements NotificationSpi {
        @Override
        public Long savePotentialProjectNotification(Event<PotentialProject> event) {
            return null;
        }

        @Override
        public Long saveProspectNotification(Event<Prospect> event) {
            return null;
        }

        @Override
        public Optional<Event<PotentialProject>> findProjectNotificationById(Long projectNotificationId) {
            return Optional.empty();
        }

        @Override
        public Optional<Event<Prospect>> findProspectNotificationById(Long prospectNotificationId) {
            return Optional.empty();
        }

        @Override
        public Optional<Event<Notify>> findNotificationById(Long id) {
            return Optional.of(new Event<Notify>(
                    1L,
                    NotificationStatus.OPEN,
                    "Message",
                    Priority.LOW,
                    new Prospect(1L, null, null, null, null, null,
                        null, null, null, null, null,
                        null),
                    EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE
                )
            );
        }

        @Override
        public List<Event<Notify>> findNotificationByElementIdAndStatusAndEventType(Long elementId, List<NotificationStatus> notificationStatuses, EventType eventType) {
            return null;
        }

        @Override
        public Event<Notify> save(Event<Notify> event) {
            return new Event<Notify>(
                1L,
                NotificationStatus.OPEN,
                "Message",
                Priority.LOW,
                new Prospect(1L, null, null, null, null, null,
                    null, null, null, null, null,
                    null),
                EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE
            );
        }

        @Override
        public List<Event<Notify>> findNotificationByEventType(EventType eventType) {
            return null;
        }
    }

    @BeforeEach
    void setUp() {
        eventManager = new EventManager(null, new NotificationSpiMock());
    }

    @Test
    void save() {
        EventResponse expected = new EventResponse(
            1L,
            NotificationStatus.OPEN.name(),
            "Message",
            Priority.LOW.name(),
            ProspectResponse.fromModel(
                new Prospect(1L, null, null, null, null,
                null, null, null, null, null, null,
                null)
            )
        );

        EventResponse actual = eventManager.save(1L, new NotificationStatusRequest(NotificationStatus.OPEN));

        assertEquals(expected, actual);
    }
}