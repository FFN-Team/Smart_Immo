package com.gangdestrois.smartimmo.domain.salesHistory;

import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.domain.salesHistory.port.SalesHistorySpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.SalesHistoryDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SalesHistoryStatisticsGeneratorTest {
    private SalesHistoryStatisticsGenerator salesHistoryStatisticsGenerator;
    private SalesHistorySpi salesHistorySpi;
    private PropertySpi propertySpi;

    @BeforeEach
    public void setUp() throws Exception {
        salesHistorySpi = mock(SalesHistoryDataAdapter.class);
        propertySpi = mock(PropertyDataAdapter.class);
        salesHistoryStatisticsGenerator = new SalesHistoryStatisticsGenerator(salesHistorySpi, propertySpi);
    }

    @Test
    public void
    getSalesHistoryComparisonStatistics_should_retrieve_sales_history_statistics_when_property_exists_and_period_valid()
    {
        // Given
        SalesHistoryComparisonStatistic buildingStatistics =
                new SalesHistoryComparisonStatistic("1 Rue Étienne Marcel", 5503.87, 0.0),
            streetStatistics =
                new SalesHistoryComparisonStatistic("Rue Étienne Marcel", 7023.31, 27.61),
            areaStatistics = new SalesHistoryComparisonStatistic("Area 1", 10730.52, 94.96),
            cityStatistics = new SalesHistoryComparisonStatistic("Paris", 21259.69, 286.27);
        List<SalesHistoryComparisonStatistic> expectedStatistics = List.of(
            buildingStatistics, streetStatistics, areaStatistics, cityStatistics
        );

        Long propertyId = 1L;
        LocalDate startDate = LocalDate.of(1970, 1, 1),
                endDate = LocalDate.of(2024, 1, 1);

        when(propertySpi.existsById(propertyId)).thenReturn(true);
        when(salesHistorySpi.findAveragePricePerSquareMeterOfPropertySBuilding(propertyId, startDate, endDate))
            .thenReturn(buildingStatistics);
        when(salesHistorySpi.findAveragePricePerSquareMeterOfPropertySStreet(propertyId, startDate, endDate))
            .thenReturn(streetStatistics);
        when(salesHistorySpi.findAveragePricePerSquareMeterOfPropertySArea(propertyId, startDate, endDate))
            .thenReturn(areaStatistics);
        when(salesHistorySpi.findAveragePricePerSquareMeterOfPropertySCity(propertyId, startDate, endDate))
            .thenReturn(cityStatistics);

        // When
        List<SalesHistoryComparisonStatistic> actualStatistics =
            salesHistoryStatisticsGenerator.getSalesHistoryComparisonStatistics(propertyId, startDate, endDate);

        // Then
        assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    public void getSalesHistoryComparisonStatistics_should_throw_bad_request_exception_when_property_does_not_exist() {
        // Given
        Long propertyId = -1L;
        LocalDate startDate = LocalDate.of(1970, 1, 1),
                endDate = LocalDate.of(2024, 1, 1);

        // When and then
        assertThrows(NotFoundException.class,
            () -> salesHistoryStatisticsGenerator.getSalesHistoryComparisonStatistics(propertyId, startDate, endDate));
    }

    @Test
    public void getSalesHistoryComparisonStatistics_should_throw_bad_request_exception_when_period_not_valid() {
        // Given
        LocalDate startDate = LocalDate.of(1890, 1, 1),
                endDate = LocalDate.of(2024, 1, 1);

        when(propertySpi.existsById(anyLong())).thenReturn(true);

        // When and then
        assertThrows(BadRequestException.class,
            () -> salesHistoryStatisticsGenerator.getSalesHistoryComparisonStatistics(anyLong(), startDate, endDate));
    }
}