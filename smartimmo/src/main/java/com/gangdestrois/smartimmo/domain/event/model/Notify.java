package com.gangdestrois.smartimmo.domain.event.model;


import com.gangdestrois.smartimmo.domain.tool.Model;

public interface Notify extends Model {
    Event<? extends Notify> mapToEvent();

    void setId(Long id);
}
