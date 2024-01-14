package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.model.Event;

public interface Notify<T extends Notify> extends Model {
    Event<T> mapToEvent();

    void setId(Long id);
}
