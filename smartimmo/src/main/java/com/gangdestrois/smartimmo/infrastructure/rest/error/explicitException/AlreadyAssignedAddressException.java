package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyAssignedAddressException extends BadRequestException implements Exception {

    public AlreadyAssignedAddressException() {
    }

    @Override
    public String getError() {
        return "AlreadyAssignedAddressException";
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getDetails() {
        return "Ensure that the property has a non-assigned address.";
    }
}
