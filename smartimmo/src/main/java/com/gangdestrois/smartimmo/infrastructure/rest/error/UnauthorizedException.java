package com.gangdestrois.smartimmo.infrastructure.rest.error;

public class UnauthorizedException extends RuntimeException implements Exception {
    private final ExceptionEnum exception;

    public UnauthorizedException(ExceptionEnum exception, String message) {
        super(message);
        this.exception = exception;
    }

    @Override
    public ExceptionEnum getError() {
        return this.exception;
    }
}
