package com.gangdestrois.smartimmo.infrastructure.rest.dto;

public record ApiErrorResponse(String error, String message, String detail) {
}