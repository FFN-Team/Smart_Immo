package com.gangdestrois.smartimmo.domain.salesHistory;

import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistorySpi;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistoryStatisticsGeneratorApi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalesHistoryStatisticsGenerator implements SalesHistoryStatisticsGeneratorApi {
    private final SalesHistorySpi salesHistorySpi;
    private final PropertySpi propertySpi;

    public SalesHistoryStatisticsGenerator(SalesHistorySpi salesHistorySpi, PropertySpi propertySpi) {
        this.salesHistorySpi = salesHistorySpi;
        this.propertySpi = propertySpi;
    }

    public List<SalesHistoryComparisonStatistic> getSalesHistoryComparisonStatistics(
            Long propertyId,
            LocalDate startDate,
            LocalDate endDate
    ) throws BadRequestException {
        boolean propertyExists = propertySpi.existsById(propertyId);
        boolean periodIsValid = Period.isValid(startDate, endDate);

        if (propertyExists && periodIsValid) {
            return new ArrayList<>(Arrays.asList(
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySBuilding(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySStreet(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySArea(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySCity(propertyId, startDate, endDate)
            ));
        } else {
            throw new NotFoundException(propertyId, "property");
        }
    }
}
