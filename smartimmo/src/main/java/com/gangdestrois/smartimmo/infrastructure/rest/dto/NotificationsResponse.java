package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import java.util.List;

public record NotificationsResponse(List<EventResponse> notifications) {
}