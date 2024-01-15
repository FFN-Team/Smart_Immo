package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public interface ProspectApi {
    List<Event<Prospect>> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);
}
