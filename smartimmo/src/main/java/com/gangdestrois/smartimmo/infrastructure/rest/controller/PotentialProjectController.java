package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.NotificationsResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.ProspectResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/potential-projects")
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

    @PostMapping("/unsubscription")
    @Operation(
            description = "Unauthorized potential projects to send notifications when due date is approaching.",
            responses = @ApiResponse(responseCode = "200", description = "Unsubscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void unsubscription() {
        potentialProjectApi.unsubscription(notificationAlertListener);
    }

    @PostMapping("/notifications")
    @Operation(description = "Update the list of notification of potential projects and " +
            "return all notifications of potentials projects that need to be recontact soon.",
            responses = @ApiResponse(responseCode = "200", description = "Update and retrieve successfully.")
    )
    public ResponseEntity<NotificationsResponse> notifyPotentialProjects() {
        var potentialProjectNotifications = potentialProjectApi.notifyPotentialProjects().stream()
                .map(EventResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new NotificationsResponse(potentialProjectNotifications));
    }

    @GetMapping("/{potential-project-id}/prospect")
    @Operation(description = """
            Get prospect from potential project
            """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update and retrieve successfully."),
                    @ApiResponse(responseCode = "400", description = "Prospect not found for this potential project id.")
            }
    )
    public ResponseEntity<ProspectResponse> getProspect(@PathVariable("potential-project-id") Long potentialProjectId) {
        return ResponseEntity.ok(potentialProjectApi.findProspectByPotentialProjectId(potentialProjectId)
                .map(ProspectResponse::fromModel).orElseThrow(() ->
                        new NotFoundException(ExceptionEnum.PROSPECT_NOT_FOUND,
                                String.format("Prospect not found for this potential project id : %d.", potentialProjectId))));
    }
}
