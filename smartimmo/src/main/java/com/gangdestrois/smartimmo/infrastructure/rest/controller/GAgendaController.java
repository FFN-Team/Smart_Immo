package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;
import com.gangdestrois.smartimmo.domain.agenda.port.AgendaApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.EmailRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.VisitRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/gagenda")
public class GAgendaController {
    private final AgendaApi agendaApi;
    public GAgendaController(AgendaApi agendaApi){this.agendaApi=agendaApi;}

    @Operation(
            summary = "Save a visit",
            description = "Adds a new visit to the google agenda",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Visit saved successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PostMapping
    public void saveVisit(@RequestBody VisitRequest visitRequest) throws IOException {
        agendaApi.addVisitToAgenda(visitRequest.toModel());
    }
}
