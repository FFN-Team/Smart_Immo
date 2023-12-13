package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PotentialBuyerEventResponse;
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
@RequestMapping("/api/v1/prospects")
public class ProspectController {
    private final ProspectApi prospectApi;
    private final NotificationAlertListener notificationAlertListener;

    public ProspectController(ProspectApi prospectApi, NotificationAlertListener notificationAlertListener) {
        this.prospectApi = prospectApi;
        this.notificationAlertListener = notificationAlertListener;
    }

    @PostMapping("/notification")
    public ResponseEntity<Set<PotentialBuyerEventResponse>> notifyPotentialProjects() {
        return ResponseEntity.ok(prospectApi.notifyForProspectsThatMayBuyBiggerHouse().stream()
                .map(PotentialBuyerEventResponse::fromModel)
                .collect(Collectors.toSet()));
    }

    @PostMapping("/subscription")
    @Operation(
            description = "Authorize to send notifications when a prospect may want to buy a new house.",
            responses = @ApiResponse(responseCode = "200", description = "Subscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void subscription() {
        prospectApi.subscription(notificationAlertListener);
    }
}
