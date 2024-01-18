package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.salesHistory.SalesHistoryComparisonStatistic;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistorySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.SalesHistoryRepository;

import java.time.LocalDate;
import java.util.List;

public class SalesHistoryDataAdapter implements SalesHistorySpi {
    private final SalesHistoryRepository salesHistoryRepository;

    public SalesHistoryDataAdapter(SalesHistoryRepository salesHistoryRepository) {
        this.salesHistoryRepository = salesHistoryRepository;
    }

    @Override
    public SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySBuilding(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return getSalesHistoryComparisonStatistic(
                salesHistoryRepository.findAveragePricePerSquareMeterOfPropertySBuilding(propertyId, startDate, endDate)
        );
    }

    @Override
    public SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySStreet(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return getSalesHistoryComparisonStatistic(
                salesHistoryRepository.findAveragePricePerSquareMeterOfPropertySStreet(propertyId, startDate, endDate)
        );
    }

    @Override
    public SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySArea(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return getSalesHistoryComparisonStatistic(
                salesHistoryRepository.findAveragePricePerSquareMeterOfPropertySArea(propertyId, startDate, endDate)
        );
    }

    @Override
    public SalesHistoryComparisonStatistic findAveragePricePerSquareMeterOfPropertySCity(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return getSalesHistoryComparisonStatistic(
                salesHistoryRepository.findAveragePricePerSquareMeterOfPropertySCity(propertyId, startDate, endDate)
        );
    }

    private SalesHistoryComparisonStatistic getSalesHistoryComparisonStatistic(List<Object[]> lines) {
        return new SalesHistoryComparisonStatistic(
                (String)lines.getFirst()[0],
                (Double)lines.getFirst()[1],
                (Double)lines.getFirst()[2]
        );
    }
}
