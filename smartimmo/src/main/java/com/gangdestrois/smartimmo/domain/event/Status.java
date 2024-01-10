package com.gangdestrois.smartimmo.domain.event;

import java.util.Arrays;
import java.util.List;

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

    public static List<Status> statusesNotAlreadyDealt() {
        return Arrays.stream(Status.values()).filter(Status::isNotAlreadyDealt).toList();
    }
}
