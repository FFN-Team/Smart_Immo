package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.project.port.ProjetAnticipeApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projets")
public class ProjetAnticipeController {
    private final ProjetAnticipeApi projetAnticipeApi;

    public ProjetAnticipeController(ProjetAnticipeApi projetAnticipeApi) {
        this.projetAnticipeApi = projetAnticipeApi;
    }
}
