package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmailSenderNotFoundExceptionException extends NotFoundException implements Exception {

    private final String resource;

    public EmailSenderNotFoundExceptionException(String resource, String message) {
        super(message);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    @Override
    public String getError() {
        return "EmailSenderNotFoundExceptionException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
