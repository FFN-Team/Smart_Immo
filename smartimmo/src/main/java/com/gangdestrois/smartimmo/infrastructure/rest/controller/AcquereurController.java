package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurApi;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.AcquereurResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acquereurs")
public class AcquereurController {
    private final AcquereurApi acquereurApi;
    private final AcquereurSpi acquereurSpi;

    public AcquereurController(AcquereurApi acquereurApi, AcquereurSpi acquereurSpi) {
        this.acquereurApi = acquereurApi;
        this.acquereurSpi=acquereurSpi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AcquereurResponse> collectAllAcquereurs() {
        return acquereurSpi.findAllAcquereurs().stream()
                .map(AcquereurResponse::fromModel).toList();
    }

    @GetMapping("/{acquereurId}")
    @ResponseStatus(HttpStatus.OK)
    public Acquereur CollectAcquereurById(@PathVariable int acquereurId) {
        Acquereur acquereur = acquereurSpi.findAcquereurById(acquereurId);

        if (acquereur != null) {
            return acquereur;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Acquereur not found");
        }
    }
}
