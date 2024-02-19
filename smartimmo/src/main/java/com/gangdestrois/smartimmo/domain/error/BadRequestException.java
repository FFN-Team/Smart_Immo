package com.gangdestrois.smartimmo.domain.error;

public class BadRequestException extends RuntimeException implements Exception {
    public final ExceptionEnum exception;

    public BadRequestException(ExceptionEnum exception, String message) {
        super(message);
        this.exception = exception;
    }

    @Override
    public ExceptionEnum getError() {
        return exception;
    }
}
