package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class GoogleUnauthorizedException extends UnauthorizedException implements Exception {
    private final String resource;

    public GoogleUnauthorizedException(String resource, String message) {
        super(message);
        this.resource = resource;
    }

    @Override
    public String getError() {
        return "GoogleUnauthorizedException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
