package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.common.DomainComponent;

@DomainComponent
public interface EventListener {
    void update(Event event);
}
