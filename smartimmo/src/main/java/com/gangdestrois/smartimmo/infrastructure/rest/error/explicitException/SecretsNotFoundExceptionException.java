package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SecretsNotFoundExceptionException extends NotFoundException implements Exception {

    public SecretsNotFoundExceptionException(String message) {
        super(message);
    }

    @Override
    public String getError() {
        return "SecretsNotFoundExceptionException";
    }

    @Override
    public String getDetails() {
        return "Google API Secrets not found.";
    }
}
