package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.model.Event;

public interface Notify extends Model {
    Event<? extends Notify> mapToEvent();

    void setId(Long id);
}
