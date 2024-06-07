package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import java.util.List;

public record NotificationsResponse(List<EventResponse> notifications) {
}