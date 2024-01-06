package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.filter.prospect.port.ProspectFilterApi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.*;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Set;
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
        this.prospectFilterApi=prospectFilterApi;
    }

    @PostMapping("/notification")
    public ResponseEntity<Set<PotentialBuyerEventResponse>> notifyPotentialProjects() {
        return ResponseEntity.ok(prospectApi.notifyForProspectsThatMayBuyBiggerHouse().stream()
                .map(PotentialBuyerEventResponse::fromModel)
                .collect(Collectors.toSet()));
    }

    @PostMapping("/subscription")
    @Operation(
            description = "Authorize to send notifications when a prospect may want to buy a new house.",
            responses = @ApiResponse(responseCode = "200", description = "Subscribe successfully."))
    @ResponseStatus(HttpStatus.OK)
    public void subscription() {
        prospectApi.subscription(notificationAlertListener);
    }


    @PostMapping("/filtred")
    @ResponseStatus(HttpStatus.OK) //à faire : mettre ProspectResponse
    public List<Prospect> filterProspects(@Valid @RequestBody @NotNull ProspectFilterRequest prospectFilterRequest){
        return prospectFilterApi.filterProspects(prospectFilterRequest.toModel());
    }

    @PutMapping("/filter")
    @ResponseStatus(HttpStatus.OK) //à faire : mettre ProspectResponse
    public ResponseEntity<String> saveProspectsFilter(@Valid @RequestBody @NotNull ProspectFilterRequest prospectFilterRequest){
        try {
            prospectFilterApi.saveProspectFilter(prospectFilterRequest.toModel());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erreur de contrainte d'intégrité : " + ex.getMessage());
        }
    }

    @GetMapping("/filters")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProspectFilterResponse>> getProspectsFilters(){
            return ResponseEntity.ok(prospectFilterApi.findAll().stream()
                    .map(ProspectFilterResponse::fromModel)
                    .toList());
    }


    @GetMapping("/existing-filter")
    @ResponseStatus(HttpStatus.OK) //à faire : mettre ProspectResponse
    public List<Prospect> filterProspectsWithExistingFilter(@Valid @RequestBody @NotNull
                                              ExistingProspectFilterRequest existingProspectFilterRequest){
        ProspectFilter existingProspectFilter;
        try {
            existingProspectFilter = prospectFilterApi.findByProspectFilterName(existingProspectFilterRequest.prospectFilterName());
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProspectFilter introuvable : " + ex.getMessage(), ex);
        }
        return prospectFilterApi.filterProspects(existingProspectFilter);
    }
}
