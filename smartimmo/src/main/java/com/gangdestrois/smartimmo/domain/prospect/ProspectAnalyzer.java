package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.enums.Status;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;

import java.util.Set;
import java.util.stream.Collectors;

import static com.gangdestrois.smartimmo.domain.event.enums.EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE;

@DomainComponent
public class ProspectAnalyzer implements ProspectApi {
    private final ProspectSpi prospectSpi;
    private final NotificationSpi notificationSpi;
    private final EventManager eventManager;

    public ProspectAnalyzer(ProspectSpi prospectSpi, NotificationSpi notificationSpi, EventManager eventManager) {
        this.prospectSpi = prospectSpi;
        this.notificationSpi = notificationSpi;
        this.eventManager = eventManager;
    }

    public Set<Event> notifyForProspectsThatMayBuyBiggerHouse() {
        findProspectsThatMayBuyBiggerHouse().stream()
                .filter(prospect -> notificationSpi.findNotificationByElementIdAndStatusAndEventType(
                        prospect.id(), Status.TO_READ, PROSPECT_MAY_BUY_BIGGER_HOUSE).size() == 0)
                .forEach(prospect -> {
                    var prospectNotification = prospect.mapToProspectNotification();
                    prospectNotification.setId(notificationSpi.saveProspectNotification(prospectNotification));
                    eventManager.notify(prospectNotification);
                });
        return eventManager.eventsFromEventType(PROSPECT_MAY_BUY_BIGGER_HOUSE).stream()
                .filter(event -> !event.status().isAlreadyDealt())
                .collect(Collectors.toSet());
    }

    public Set<Prospect> findProspectsThatMayBuyBiggerHouse() {
        var prospectMayBuyBiggerHouseCriteria = new ProspectMayBuyBiggerHouseCriteria();
        return prospectSpi.findAll().stream()
                .filter(prospectMayBuyBiggerHouseCriteria.combinedPredicate::test)
                .collect(Collectors.toSet());
    }

    @Override
    public void subscription(EventListener eventListener) {
        eventManager.subscribe(PROSPECT_MAY_BUY_BIGGER_HOUSE, eventListener);
    }

}
