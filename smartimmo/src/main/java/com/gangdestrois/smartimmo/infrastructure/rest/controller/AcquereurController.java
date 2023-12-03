package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.AcquereurAvecCritereBienResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/acquereurs")
public class AcquereurController {
    private final AcquereurApi acquereurApi;

    public AcquereurController(AcquereurApi acquereurApi) {
        this.acquereurApi = acquereurApi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AcquereurAvecCritereBienResponse> collectAllAcquereurs() {
        return acquereurApi.collectAllAcquereurs().stream().map(AcquereurAvecCritereBienResponse::fromModel).toList();
    }
}
