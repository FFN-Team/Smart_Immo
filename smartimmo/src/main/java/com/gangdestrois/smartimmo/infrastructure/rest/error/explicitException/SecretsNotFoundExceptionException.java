package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SecretsNotFoundExceptionException extends NotFoundException implements Exception {

    private final String resource;

    public SecretsNotFoundExceptionException(String resource, String message) {
        super(message);
        this.resource = resource;
    }

    @Override
    public String getError() {
        return "SecretsNotFoundExceptionException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
