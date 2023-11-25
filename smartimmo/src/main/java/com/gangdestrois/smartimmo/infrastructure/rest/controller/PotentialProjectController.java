package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.project.port.PotentialProjectApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PotentialProjectEventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projets")
public class PotentialProjectController {
    private final PotentialProjectApi potentialProjectApi;

    public PotentialProjectController(PotentialProjectApi potentialProjectApi) {
        this.potentialProjectApi = potentialProjectApi;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<PotentialProjectEventResponse> retrieve() {
        return potentialProjectApi.notifyPotentialProjects().stream()
                .map(PotentialProjectEventResponse::fromModel)
                .collect(Collectors.toSet());
    }
}
