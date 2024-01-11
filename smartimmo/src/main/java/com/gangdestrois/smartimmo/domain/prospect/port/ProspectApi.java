package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.Set;

public interface ProspectApi {
    Set<Event> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);
}
