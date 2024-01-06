package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
