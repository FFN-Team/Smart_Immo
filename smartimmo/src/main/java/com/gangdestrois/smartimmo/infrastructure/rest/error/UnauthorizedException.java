package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
