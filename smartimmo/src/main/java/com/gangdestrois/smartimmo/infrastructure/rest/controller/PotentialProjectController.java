package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.notification.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.project.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PotentialProjectEventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/anticipatedProjects")
public class PotentialProjectController {
    private final PotentialProjectApi potentialProjectApi;
    private final NotificationAlertListener notificationAlertListener;

    public PotentialProjectController(PotentialProjectApi potentialProjectApi, NotificationAlertListener notificationAlertListener) {
        this.potentialProjectApi = potentialProjectApi;
        this.notificationAlertListener = notificationAlertListener;
    }

    @PostMapping("/subscription")
    @ResponseStatus(HttpStatus.OK)
    public void subscription(){
        potentialProjectApi.subscription(notificationAlertListener);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PotentialProjectEventResponse> notifyPotentialProjects() {
        return potentialProjectApi.notifyPotentialProjects().stream()
                .map(PotentialProjectEventResponse::fromModel)
                .collect(Collectors.toSet());
    }
}
