package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.email.port.EmailApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.EmailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = "send email to prospect based on an eventType",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Email send successfully."),
                    @ApiResponse(responseCode = "400", description = "The given prospectId does not correspond to any prospect."),
                    @ApiResponse(responseCode = "401", description = "This prospect does not wish to be contacted via social networks."),
            })
    public void sendEmail(@RequestBody EmailRequest emailRequest) throws Exception {
        emailApi.configAndSendEmail(emailRequest.prospectId(), emailRequest.eventType());
    }
}