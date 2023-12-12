package com.gangdestrois.smartimmo.domain.event;

public enum State {
    TO_READ(false),
    OPEN(false),
    DEALT(true),
    ARCHIVED(true);
    private boolean isAlreadyDealt;

    State(boolean isAlreadyDealt) {
    }

    public boolean isAlreadyDealt() {
        return this.isAlreadyDealt;
    }

    public boolean isNotAlreadyDealt() {
        return !this.isAlreadyDealt();
    }
}
