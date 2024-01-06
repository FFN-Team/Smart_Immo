package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
