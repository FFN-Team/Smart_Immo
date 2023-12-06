package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PotentialProjectEventResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(
            description = "Authorize potential projects to send notifications when due date is approaching.",
            responses = @ApiResponse(responseCode = "200", description = "Subscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void subscription() {
        potentialProjectApi.subscription(notificationAlertListener);
    }

    @PostMapping("/notification")
    @Operation(description = "Update the list of notification of potential projects and " +
            "return all notifications of potentials projects that need to be recontact soon.",
            responses = @ApiResponse(responseCode = "200", description = "Update and retrieve successfully.")
    )
    public ResponseEntity<Set<PotentialProjectEventResponse>> notifyPotentialProjects() {
        return ResponseEntity.ok(potentialProjectApi.notifyPotentialProjects().stream()
                .map(PotentialProjectEventResponse::fromModel)
                .collect(Collectors.toSet()));
    }
}
