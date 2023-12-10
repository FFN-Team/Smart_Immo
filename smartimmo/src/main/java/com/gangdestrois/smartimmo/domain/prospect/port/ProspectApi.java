package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.Event;

import java.util.Set;

public interface ProspectApi {
    Set<Event> notifyForProspectsThatMayBuyBiggerHouse();
}
