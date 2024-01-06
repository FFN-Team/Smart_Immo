package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ContactOnSocialMediaUnauthorizedException extends UnauthorizedException implements Exception {
    private final Long id;
    private final String resource;

    public ContactOnSocialMediaUnauthorizedException(Long id, String resource, String message) {
        super(message);
        this.id = id;
        this.resource = resource;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getError() {
        return "ContactOnSocialMediaUnauthorizedException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
