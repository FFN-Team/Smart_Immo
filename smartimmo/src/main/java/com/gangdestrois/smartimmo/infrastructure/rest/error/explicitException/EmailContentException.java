package com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException;

import com.gangdestrois.smartimmo.infrastructure.rest.error.Exception;
import com.gangdestrois.smartimmo.infrastructure.rest.error.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailContentException extends InternalServerErrorException implements Exception {
    private final String to;
    private final String from;
    private final String content;

    public EmailContentException(String from, String to, String content, String message) {
        super(message);
        this.to = to;
        this.from = from;
        this.content = content;
    }

    @Override
    public String getError() {
        return "EmailContentException";
    }

    @Override
    public String getDetails() {
        return "to do";
    }
}
