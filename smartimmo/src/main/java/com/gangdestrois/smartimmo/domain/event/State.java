package com.gangdestrois.smartimmo.domain.event;

public enum State {
    OPEN(false),
    TO_READ(false),
    DEALT(true),
    ARCHIVED(true);
    private boolean isAlreadyDealt;

    State(boolean isAlreadyDealt) {
    }

    public boolean isAlreadyDealt() {
        return this.isAlreadyDealt;
    }
}
