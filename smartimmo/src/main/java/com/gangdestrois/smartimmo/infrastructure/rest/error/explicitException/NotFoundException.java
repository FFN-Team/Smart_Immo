package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements Exception {
    private final Long id;
    private final String resource;

    public NotFoundException(Long id, String resource) {
        this.id = id;
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }

    @Override
    public String getError() {
        return "NotFoundException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
