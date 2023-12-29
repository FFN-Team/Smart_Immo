package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.StateRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
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

    @PatchMapping("/{notificationId}/state")
    @Operation(
        summary = "Update the state of a notification by id.",
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
    public ResponseEntity<EventResponse> changeState(@PathVariable Long notificationId, @Valid @RequestBody StateRequest stateRequest) {
        Event originalEvent = notificationApi.findNotificationById(notificationId)
                .orElseThrow(() -> new NotFoundException(notificationId, "notification"));
        Event eventToSave = new Event<>(
                notificationId,
                stateRequest.state(),
                originalEvent.message(),
                originalEvent.priority(),
                originalEvent.getElement()
        );
        Event savedEvent = notificationApi.save(eventToSave);
        EventResponse response = EventResponse.fromModel(savedEvent);
        return ResponseEntity.ok(response);
    }
}
