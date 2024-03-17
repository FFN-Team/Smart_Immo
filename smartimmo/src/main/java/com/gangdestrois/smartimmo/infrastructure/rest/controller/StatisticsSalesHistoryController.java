package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.salesHistory.SalesHistoryComparisonStatistic;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistoryStatisticsGeneratorApi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics/sales-history")
public class StatisticsSalesHistoryController {
    private final SalesHistoryStatisticsGeneratorApi salesHistoryStatisticsGeneratorApi;

    public StatisticsSalesHistoryController(SalesHistoryStatisticsGeneratorApi salesHistoryStatisticsGeneratorApi) {
        this.salesHistoryStatisticsGeneratorApi = salesHistoryStatisticsGeneratorApi;
    }

    @GetMapping("/properties/{propertyId}")
    @Operation(
        summary = "Get price comparison statistics compared to a property over a given period.",
        description = "Returns price comparison statistics compared to a property over a given period.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Price comparison statistics retrieve successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Resource not found.")
        }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SalesHistoryComparisonStatistic>> getSalesHistoryComparisonStatistics(
            @PathVariable Long propertyId,
            @RequestParam("start-date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate
    ) {
        return ResponseEntity.ok(
                salesHistoryStatisticsGeneratorApi.getSalesHistoryComparisonStatistics(propertyId, startDate, endDate)
        );
    }
}
