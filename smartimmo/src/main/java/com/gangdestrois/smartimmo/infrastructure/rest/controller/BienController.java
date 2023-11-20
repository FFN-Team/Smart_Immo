package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.bien.port.api.BienApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BienResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/biens")
public class BienController {
    private final BienApi bienApi;

    public BienController(BienApi bienApi) {
        this.bienApi = bienApi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BienResponse> retrieve() {
        return bienApi.retrieve().stream().map(BienResponse::fromModel).toList();
    }
}
