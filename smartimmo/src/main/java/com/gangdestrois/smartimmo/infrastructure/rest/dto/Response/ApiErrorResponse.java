package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;

public record ApiErrorResponse(ExceptionEnum error, String message) {
}