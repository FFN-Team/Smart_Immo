package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.prospect.port.ProspectStatisticsGeneratorApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.ProspectStatisticsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics/prospects")
public class StatisticsProspectController {
    private final ProspectStatisticsGeneratorApi prospectStatisticsGeneratorApi;

    public StatisticsProspectController(ProspectStatisticsGeneratorApi prospectStatisticsGeneratorApi) {
        this.prospectStatisticsGeneratorApi = prospectStatisticsGeneratorApi;
    }

    @GetMapping("/count-by-age-group")
    @Operation(
            summary = "Retrieve the number of prospects by age group.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Number of prospects by age group retrieves with success."
                    )
            }
    )
    public ResponseEntity<ProspectStatisticsResponse> countByAgeGroup() {
        return ResponseEntity.ok(prospectStatisticsGeneratorApi.countByAgeGroup());
    }

    @GetMapping("/count-by-profession")
    @Operation(
            summary = "Retrieve the number of prospects by profession.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Number of prospects by profession retrieves with success."
                    )
            }
    )
    public ResponseEntity<ProspectStatisticsResponse> countByProfession() {
        return ResponseEntity.ok(prospectStatisticsGeneratorApi.countByProfession());
    }

    @GetMapping("/count-by-contact-origin")
    @Operation(
            summary = "Retrieve the number of prospects by contact origin.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Number of prospects by contact origin retrieves with success."
                    )
            }
    )
    public ResponseEntity<ProspectStatisticsResponse> countByContactOrigin() {
        return ResponseEntity.ok(prospectStatisticsGeneratorApi.countByContactOrigin());
    }
}
