package com.gangdestrois.smartimmo.domain.event;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class ProspectNotification extends Event {
    private final Prospect prospect;

    public ProspectNotification(Prospect prospect, State state, String message, Priority priority) {
        super(state, message, priority);
        this.prospect = prospect;
    }

    public Prospect prospect() {
        return this.prospect;
    }


}
