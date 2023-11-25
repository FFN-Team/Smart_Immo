package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.project.port.PotentialProjectApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projets")
public class PotentialProjectController {
    private final PotentialProjectApi potentialProjectApi;

    public PotentialProjectController(PotentialProjectApi potentialProjectApi) {
        this.potentialProjectApi = potentialProjectApi;
    }
}
