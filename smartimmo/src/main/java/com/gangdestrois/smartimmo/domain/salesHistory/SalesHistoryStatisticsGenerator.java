package com.gangdestrois.smartimmo.domain.salesHistory;

import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistorySpi;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistoryStatisticsGeneratorApi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum.PERIOD_NOT_VALID;

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
            LocalDate endDate) throws BadRequestException {
        propertySpi.findById(propertyId).orElseThrow(() -> new NotFoundException(ExceptionEnum.PROPERTY_NOT_FOUND,
                String.format("Property not found for this id : %d", propertyId)));
        var periodErrors = SalesHistoryPeriodUtils.findValidationsErrors(startDate, endDate);
        if (periodErrors.isEmpty()) {
            return new ArrayList<>(Arrays.asList(
                    // envoyer le property plutôt que le propertyId
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySBuilding(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySStreet(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySArea(propertyId, startDate, endDate),
                    salesHistorySpi.findAveragePricePerSquareMeterOfPropertySCity(propertyId, startDate, endDate)
            ));
        }
        throw new BadRequestException(PERIOD_NOT_VALID, String.join(" ; ", periodErrors));
    }
}
