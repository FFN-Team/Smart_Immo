package com.gangdestrois.smartimmo.domain.error;

public class InternalServerErrorException extends RuntimeException implements Exception {
    private final ExceptionEnum exception;

    public InternalServerErrorException(ExceptionEnum exception, String message) {
        super(message);
        this.exception = exception;
    }

    @Override
    public ExceptionEnum getError() {
        return exception;
    }
}
