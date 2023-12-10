package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/statistics/prospects")
public class StatisticsProspectController {
    private final ProspectApi prospectApi;

    public StatisticsProspectController(ProspectApi prospectApi) {
        this.prospectApi = prospectApi;
    }

    @GetMapping
    @RequestMapping("/count-by-age-group")
    @Operation(
        summary = "Retrieve the number of prospects by age group.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Number of prospects by age group retrieves with success."
            )
        }
    )
    public ResponseEntity<Map<String, Map<String, Long>>> countByAgeGroup() {
        return ResponseEntity.ok(prospectApi.countByAgeGroup());
    }

    @GetMapping
    @RequestMapping("/count-by-profession")
    @Operation(
        summary = "Retrieve the number of prospects by profession.",
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Number of prospects by profession retrieves with success."
            )
        }
    )
    public ResponseEntity<Map<String, Map<String, Long>>> countByProfession() {
        return ResponseEntity.ok(prospectApi.countByProfession());
    }

    @GetMapping
    @RequestMapping("/count-by-contact-origin")
    @Operation(
        summary = "Retrieve the number of prospects by contact origin.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Number of prospects by contact origin retrieves with success."
            )
        }
    )
    public ResponseEntity<Map<String, Map<String, Long>>> countByContactOrigin() {
        return ResponseEntity.ok(prospectApi.countByContactOrigin());
    }
}
