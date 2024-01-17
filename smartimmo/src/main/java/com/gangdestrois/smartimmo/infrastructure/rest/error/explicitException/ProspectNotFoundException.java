package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProspectNotFoundException extends NotFoundException implements Exception {
    public ProspectNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getError() {
        return "ProspectNotFoundException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}