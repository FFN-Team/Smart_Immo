package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.notification.Notification;

import java.time.LocalDate;

public class ProjetAnticipe {
    private Long id;
    private LocalDate datePrevue;

    public ProjetAnticipe(Long id, LocalDate datePrevue) {
        this.datePrevue = datePrevue;
    }

    public Notification mapToEvent() {
        return new Notification();
    }
}
