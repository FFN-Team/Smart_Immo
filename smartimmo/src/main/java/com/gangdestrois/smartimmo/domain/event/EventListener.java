package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.model.Notify;

@DomainComponent
public interface EventListener {
    void update(Event<? extends Notify> event);
}
