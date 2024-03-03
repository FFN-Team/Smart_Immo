package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.ProspectNotificationStrategy;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Event<Prospect>> notifyForProspectsThatMayBuyBiggerHouse() {
        var prospectsToNotify = findProspectsThatMayBuyBiggerHouse();
        var prospectNotificationStrategy = new ProspectNotificationStrategy(this.notificationSpi, eventManager);
        prospectNotificationStrategy.makeNotification(prospectsToNotify,
                PROSPECT_MAY_BUY_BIGGER_HOUSE);
        return prospectNotificationStrategy.getNotifications(PROSPECT_MAY_BUY_BIGGER_HOUSE);
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

    @Override
    public void unsubscription(EventListener eventListener) {
        eventManager.unSubscribe(PROSPECT_MAY_BUY_BIGGER_HOUSE, eventListener);
    }

    @Override
    public List<Prospect> getProspects() {
        return prospectSpi.findAll();
    }

    @Override
    public Optional<Prospect> getProspect(Long id) {
        return prospectSpi.findById(id);
    }

}
