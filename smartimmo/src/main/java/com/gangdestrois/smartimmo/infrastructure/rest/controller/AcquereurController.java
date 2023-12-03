package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurApi;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.domain.acquereur.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.bien.Bien;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.AcquereurResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BienResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acquereurs")
public class AcquereurController {
    private final PropertiesFinderApi propertiesFinderApi;
    private final AcquereurSpi acquereurSpi;

    public AcquereurController(PropertiesFinderApi propertiesFinderApi, AcquereurSpi acquereurSpi) {
        this.propertiesFinderApi = propertiesFinderApi;
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
    public AcquereurResponse CollectAcquereurById(@PathVariable int acquereurId) {
        Acquereur acquereur = acquereurSpi.findAcquereurById(acquereurId);

        if (acquereur != null) {
            return AcquereurResponse.fromModel(acquereur);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Acquereur not found");
        }
    }

    @GetMapping("/{acquereurId}/filtred-biens")
    @ResponseStatus(HttpStatus.OK)
    public List<BienResponse> findPropertiesForBuyer(@PathVariable int acquereurId) {

        List<Bien> biensFiltred = propertiesFinderApi.findPropertiesForBuyer(acquereurId);

        if (biensFiltred != null) {
            return biensFiltred
                    .stream()
                    .map(BienResponse::fromModel)
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Biens not found");
        }

    }
}
