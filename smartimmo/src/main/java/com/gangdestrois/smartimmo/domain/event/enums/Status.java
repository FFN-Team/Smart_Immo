package com.gangdestrois.smartimmo.domain.event.enums;

public enum Status {
    TO_READ(false),
    OPEN(false),
    DEALT(true),
    ARCHIVED(true);
    private boolean isAlreadyDealt;

    Status(boolean isAlreadyDealt) {
    }

    public boolean isAlreadyDealt() {
        return this.isAlreadyDealt;
    }

    public boolean isNotAlreadyDealt() {
        return !this.isAlreadyDealt();
    }
}
