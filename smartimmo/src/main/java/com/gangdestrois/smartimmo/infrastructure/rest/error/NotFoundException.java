package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class NotFoundException extends RuntimeException implements Exception {
    private final ExceptionEnum exception;

    public NotFoundException(ExceptionEnum exception, String message) {
        super(message);
        this.exception = exception;
    }

    @Override
    public ExceptionEnum getError() {
        return this.exception;
    }
}
