package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.port.SubscriptionApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.IsSubscribeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionApi subscriptionApi;

    public SubscriptionController(SubscriptionApi subscriptionApi) {
        this.subscriptionApi = subscriptionApi;
    }

    @GetMapping("/notifications/{eventType}")
    @Operation(summary = "Retrurn true if eventType is subscribed to notifications.",
            responses = {@ApiResponse(responseCode = "200")})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IsSubscribeResponse> isNotificationSubscribe(@PathVariable EventType eventType) {
        return ResponseEntity.ok(new IsSubscribeResponse(subscriptionApi.isNotificationSubscribe(eventType)));
    }

}
