package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.Model;

public interface Notify<T extends Notify> extends Model {
    Event<T> mapToEvent();

    void setId(Long id);
}
