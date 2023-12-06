package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.bien.port.BienApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BienResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Operation(summary = "Retrieve all real properties saved.", responses = {
            @ApiResponse(responseCode = "200", description = "All real properties retrieve with success.")
    })
    public ResponseEntity<List<BienResponse>> retrieve() {
        return ResponseEntity.ok(bienApi.retrieve().stream().map(BienResponse::fromModel).toList());
    }
}
