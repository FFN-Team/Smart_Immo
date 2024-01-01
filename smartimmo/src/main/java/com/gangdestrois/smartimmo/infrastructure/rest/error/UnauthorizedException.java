package com.gangdestrois.smartimmo.infrastructure.rest.error;

import com.gangdestrois.smartimmo.domain.statusCode.HttpStatusCode;

public class UnauthorizedException extends RuntimeException {
    private final Long id;
    private final HttpStatusCode httpStatusCode;
    private final String resource;
    private final String message;

    public UnauthorizedException(Long id, HttpStatusCode httpStatusCode, String resource, String message) {
        this.id = id;
        this.httpStatusCode = httpStatusCode;
        this.resource = resource;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
