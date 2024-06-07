package com.gangdestrois.smartimmo.domain.salesHistory.port;

import com.gangdestrois.smartimmo.domain.salesHistory.SalesHistoryComparisonStatistic;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;

import java.time.LocalDate;
import java.util.List;

public interface SalesHistoryStatisticsGeneratorApi {
    List<SalesHistoryComparisonStatistic> getSalesHistoryComparisonStatistics(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) throws BadRequestException;
}
