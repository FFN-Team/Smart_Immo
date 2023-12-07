package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.domain.acquereur.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.bien.entite.Bien;
import com.gangdestrois.smartimmo.domain.portfolio.entite.PortfolioBienASuivre;
import com.gangdestrois.smartimmo.domain.portfolio.port.PortfolioBienASuivreManagerApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.AcquereurResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BienResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PortfolioBASResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/acquereurs")
public class AcquereurController {
    private final PropertiesFinderApi propertiesFinderApi;
    private final AcquereurSpi acquereurSpi;
    private final PortfolioBienASuivreManagerApi portfolioBienASuivreManagerApi;

    public AcquereurController(PropertiesFinderApi propertiesFinderApi, AcquereurSpi acquereurSpi,
                               PortfolioBienASuivreManagerApi portfolioBienASuivreManagerApi) {
        this.propertiesFinderApi = propertiesFinderApi;
        this.acquereurSpi=acquereurSpi;
        this.portfolioBienASuivreManagerApi=portfolioBienASuivreManagerApi;
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
    public List<BienResponse> findPropertiesForBuyer(@PathVariable int acquereurId)
    {
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

    @GetMapping("/{acquereurId}/portfolio-BAS")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioBASResponse createPortfolioBienASuivreForBuyer(@PathVariable int acquereurId) {
        PortfolioBienASuivre portfolioBienASuivre= portfolioBienASuivreManagerApi.createPortfolioBienASuivre(acquereurId);
        return PortfolioBASResponse.fromModel(portfolioBienASuivre);
    }
}
