package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.filter.prospect.port.ProspectFilterApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.ExistingProspectFilterRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.ProspectFilterRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.EventResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.NotificationsResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.ProspectFilterResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.ProspectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/prospects")
public class ProspectController {
    private final ProspectApi prospectApi;
    private final ProspectFilterApi prospectFilterApi;
    private final NotificationAlertListener notificationAlertListener;

    public ProspectController(ProspectApi prospectApi, NotificationAlertListener notificationAlertListener,
                              ProspectFilterApi prospectFilterApi) {
        this.prospectApi = prospectApi;
        this.notificationAlertListener = notificationAlertListener;
        this.prospectFilterApi = prospectFilterApi;
    }

    @PostMapping("/notifications")
    public ResponseEntity<NotificationsResponse> notifyPotentialProjects() {
        var prospects = prospectApi.notifyForProspectsThatMayBuyBiggerHouse().stream()
                .map(EventResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new NotificationsResponse(prospects));
    }

    @PostMapping("/subscription")
    @Operation(
            description = "Authorize to send notifications when a prospect may want to buy a new house.",
            responses = @ApiResponse(responseCode = "200", description = "Subscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void subscription() {
        prospectApi.subscription(notificationAlertListener);
    }

    @PostMapping("/unsubscription")
    @Operation(
            description = "unauthorized to send notifications when a prospect may want to buy a new house.",
            responses = @ApiResponse(responseCode = "200", description = "Unsubscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void unsubscription() {
        prospectApi.unsubscription(notificationAlertListener);
    }

    @PutMapping("/filter")
    @Operation(
            summary = "Save prospects filter",
            description = "Saves the filter criteria for prospects.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Request body containing the filter criteria.",
                    required = true, content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProspectFilterRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prospects filter saved successfully."),
                    @ApiResponse(responseCode = "400", description = "Invalid request body or bad request."),
                    @ApiResponse(responseCode = "409", description = "Conflict - Integrity constraint violation.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> saveProspectsFilter(@Valid @RequestBody @NotNull ProspectFilterRequest prospectFilterRequest) {
        try {
            prospectFilterApi.saveProspectFilter(prospectFilterRequest.toModel());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erreur de contrainte d'intégrité : " + ex.getMessage());
        }
    }


    @DeleteMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Integer deleteByProspectFilterName(@Valid @RequestBody @NotNull
                                              ExistingProspectFilterRequest existingProspectFilterRequest) {
        return prospectFilterApi.deleteByProspectFilterName(existingProspectFilterRequest.prospectFilterName());
    }


    @PostMapping("/filtred")
    @Operation(
            summary = "Filter prospects",
            description = "Filters prospects based on the provided filter criteria.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing the filter criteria.", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProspectFilterRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully filtered prospects."),
                    @ApiResponse(responseCode = "400", description = "Invalid request body or bad request.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<ProspectResponse> filterProspects(@Valid @RequestBody @NotNull ProspectFilterRequest prospectFilterRequest) {
        return prospectFilterApi.filterProspects(prospectFilterRequest.toModel()).stream()
                .map(ProspectResponse::fromModel).toList();
    }


    @GetMapping("/filters")
    @Operation(
            summary = "Get all prospects filters",
            description = "Retrieves a list of all saved prospects filters.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved the list of prospects filters.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProspectFilterResponse>> getProspectsFilters() {
        return ResponseEntity.ok(prospectFilterApi.findAll().stream()
                .map(ProspectFilterResponse::fromModel)
                .toList());
    }

    @PostMapping("/existing-filter")
    @Operation(
            summary = "Filter prospects with existing filter",
            description = "Filters prospects based on an existing filter.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing the existing filter criteria.", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExistingProspectFilterRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully filtered prospects based on the existing filter."),
                    @ApiResponse(responseCode = "400", description = "Invalid request body or bad request."),
                    @ApiResponse(responseCode = "404", description = "ProspectFilter not found.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<ProspectResponse> filterProspectsWithExistingFilter(@Valid @RequestBody @NotNull
                                                                    ExistingProspectFilterRequest existingProspectFilterRequest) {
        ProspectFilter existingProspectFilter;
        try {
            existingProspectFilter = prospectFilterApi.findByProspectFilterName(existingProspectFilterRequest.prospectFilterName());
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProspectFilter introuvable : " + ex.getMessage(), ex);
        }
        return prospectFilterApi.filterProspects(existingProspectFilter).stream()
                .map(ProspectResponse::fromModel).toList();
    }

    @GetMapping()
    public List<ProspectResponse> getAllProspects() {
        return prospectApi.getProspects().stream()
                .map(ProspectResponse::fromModel)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProspectResponse getProspects(@PathVariable Long id) {
        return prospectApi.getProspect(id)
                .map(ProspectResponse::fromModel)
                .orElse(null);
    }
}
