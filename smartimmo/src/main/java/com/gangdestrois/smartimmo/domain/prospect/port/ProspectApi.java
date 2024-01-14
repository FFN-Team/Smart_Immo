package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.event.EventListener;
import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.event.model.Event;

import java.util.List;

public interface ProspectApi {
    List<Event<? extends Notify>> notifyForProspectsThatMayBuyBiggerHouse();

    void subscription(EventListener eventListener);
}
