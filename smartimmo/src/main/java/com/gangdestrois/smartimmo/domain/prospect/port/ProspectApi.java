package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface ProspectApi {
    List<Event<Prospect>> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);

    void unsubscription(EventListener eventListener);

    List<Prospect> getProspects();

    Optional<Prospect> getProspect(Long id);
}
