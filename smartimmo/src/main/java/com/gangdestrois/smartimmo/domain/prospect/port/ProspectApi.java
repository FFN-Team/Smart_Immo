package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventListener;

import java.util.Set;

public interface ProspectApi {
    Set<Event> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);
}
