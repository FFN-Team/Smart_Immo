package com.gangdestrois.smartimmo.domain.event.enums;

import java.util.Arrays;
import java.util.List;

public enum NotificationStatus {
    TO_READ(false),
    OPEN(false),
    DEALT(true),
    ARCHIVED(true);
    private final boolean isAlreadyDealt;

    NotificationStatus(boolean isAlreadyDealt) {
        this.isAlreadyDealt = isAlreadyDealt;
    }

    public boolean isAlreadyDealt() {
        return this.isAlreadyDealt;
    }

    public boolean isNotAlreadyDealt() {
        return !this.isAlreadyDealt();
    }

    public static List<NotificationStatus> statusesNotAlreadyDealt() {
        return Arrays.stream(NotificationStatus.values()).filter(NotificationStatus::isNotAlreadyDealt).toList();
    }
}
