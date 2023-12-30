package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.email.port.EmailApi;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.EmailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final EmailApi emailApi;

    public EmailController(EmailApi emailApi) {
        this.emailApi = emailApi;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws Exception {
        emailApi.configAndSendEmail(emailRequest.prospect(), EventType.valueOf(emailRequest.eventType()));
    }
}