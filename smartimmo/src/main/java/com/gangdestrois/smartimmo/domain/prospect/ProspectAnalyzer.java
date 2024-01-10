package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.ProspectNotificationStrategy;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;

import java.util.List;

import static com.gangdestrois.smartimmo.domain.event.EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE;

@DomainComponent
public class ProspectAnalyzer implements ProspectApi {
    private final ProspectSpi prospectSpi;
    private final NotificationSpi notificationSpi;
    private final EventManager<Prospect> eventManager;

    public ProspectAnalyzer(ProspectSpi prospectSpi, NotificationSpi notificationSpi, EventManager<Prospect> eventManager) {
        this.prospectSpi = prospectSpi;
        this.notificationSpi = notificationSpi;
        this.eventManager = eventManager;
    }

    public List<Event<Prospect>> notifyForProspectsThatMayBuyBiggerHouse() {
        var prospectsToNotify = findProspectsThatMayBuyBiggerHouse();
        return eventManager.makeNotifications(prospectsToNotify, PROSPECT_MAY_BUY_BIGGER_HOUSE,
                new ProspectNotificationStrategy(this.notificationSpi));
    }

    public List<Prospect> findProspectsThatMayBuyBiggerHouse() {
        var prospectMayBuyBiggerHouseCriteria = new ProspectMayBuyBiggerHouseCriteria();
        return prospectSpi.findAll().stream()
                .filter(prospectMayBuyBiggerHouseCriteria.combinedPredicate)
                .toList();
    }

    @Override
    public void subscription(EventListener eventListener) {
        eventManager.subscribe(PROSPECT_MAY_BUY_BIGGER_HOUSE, eventListener);
    }

}
