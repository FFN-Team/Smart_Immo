package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.NotificationStatusRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationApi notificationApi;

    public NotificationController(NotificationApi notificationApi) {
        this.notificationApi = notificationApi;
    }

    @PatchMapping("/{notificationId}/status")
    @Operation(
            summary = "Update the status of a notification by id.",
            description = "Returns the updated notification.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found."
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EventResponse> changeStatus(
            @PathVariable Long notificationId,
            @Valid @RequestBody NotificationStatusRequest notificationStatusRequest
    ) {
        EventResponse response = notificationApi.save(notificationId, notificationStatusRequest);
        return ResponseEntity.ok(response);
    }
    // TODO : faire la logique métier dans le domaine
}
