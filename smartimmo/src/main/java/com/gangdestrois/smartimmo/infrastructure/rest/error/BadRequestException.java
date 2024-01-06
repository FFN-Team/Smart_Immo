package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
    public BadRequestException(){
    }
}
