package com.gangdestrois.smartimmo.domain.error;

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
