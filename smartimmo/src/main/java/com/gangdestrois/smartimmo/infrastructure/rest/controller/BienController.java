package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.bien.port.BienSpi;
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
    private final BienSpi bienSpi;

    public BienController(BienSpi bienSpi) {
        this.bienSpi = bienSpi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BienResponse> retrieve() {
        return bienSpi.findAll().stream().map(BienResponse::fromModel).toList();
    }
}