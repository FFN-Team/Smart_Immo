package com.gangdestrois.smartimmo.domain.salesHistory.port;

import com.gangdestrois.smartimmo.domain.salesHistory.SalesHistoryComparisonStatistic;

import java.time.LocalDate;

public interface SalesHistorySpi {
    SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySBuilding(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    );

    SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySStreet(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    );

    SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySArea(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    );

    SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySCity(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    );
}
