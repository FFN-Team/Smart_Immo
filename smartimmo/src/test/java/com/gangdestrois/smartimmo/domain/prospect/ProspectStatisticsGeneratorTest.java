package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.ProspectDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectDataResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProspectStatisticsGeneratorTest {
    private ProspectStatisticsGenerator prospectStatisticsGenerator;
    private ProspectSpi prospectSpi;

    @BeforeEach
    public void setUp() {
        prospectSpi = mock(ProspectDataAdapter.class);
        prospectStatisticsGenerator = new ProspectStatisticsGenerator(prospectSpi);
    }

    @Test
    public void countByAgeGroup_should_retrieve_age_group_statistics_everytime() {
        // Given
        ProspectStatisticsResponse expectedStatistics = new ProspectStatisticsResponse(
            "Number of prospects by age group",
            List.of(
                new ProspectDataResponse("0 - 20", 5),
                new ProspectDataResponse("21 - 40", 10),
                new ProspectDataResponse("41 - 60", 15),
                new ProspectDataResponse("61 - 80", 20),
                new ProspectDataResponse("81 - 120", 25)
            )
        );
        when(prospectSpi.countByAgeBetween(0, 20)).thenReturn(5L);
        when(prospectSpi.countByAgeBetween(21, 40)).thenReturn(10L);
        when(prospectSpi.countByAgeBetween(41, 60)).thenReturn(15L);
        when(prospectSpi.countByAgeBetween(61, 80)).thenReturn(20L);
        when(prospectSpi.countByAgeBetween(81, 120)).thenReturn(25L);

        // When
        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByAgeGroup();

        // Then
        assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    public void countByProfession_should_retrieve_profession_statistics_everytime() {
        // Given
        ProspectStatisticsResponse expectedStatistics = new ProspectStatisticsResponse(
            "Number of prospects by profession",
            List.of(
                new ProspectDataResponse("Doctor", 5),
                new ProspectDataResponse("Engineer", 10),
                new ProspectDataResponse("Teacher", 15),
                new ProspectDataResponse("Student", 20),
                new ProspectDataResponse("Commercial", 25)
            )
        );
        when(prospectSpi.countByProfession()).thenReturn(
            List.of(
                new ProspectStatistic("Doctor", 5),
                new ProspectStatistic("Engineer", 10),
                new ProspectStatistic("Teacher", 15),
                new ProspectStatistic("Student", 20),
                new ProspectStatistic("Commercial", 25)
            )
        );

        // When
        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByProfession();

        // Then
        assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    public void countByContactOrigin_should_retrieve_contact_origin_statistics_everytime() {
        // Given
        ProspectStatisticsResponse expectedStatistics = new ProspectStatisticsResponse(
            "Number of prospects by contact origin",
            List.of(
                new ProspectDataResponse("Email", 5),
                new ProspectDataResponse("Phone", 10),
                new ProspectDataResponse("Social Media", 15),
                new ProspectDataResponse("Web Site", 20),
                new ProspectDataResponse("Word of Mouth", 25)
            )
        );
        when(prospectSpi.countByContactOrigin()).thenReturn(
            List.of(
                new ProspectStatistic("Email", 5),
                new ProspectStatistic("Phone", 10),
                new ProspectStatistic("Social Media", 15),
                new ProspectStatistic("Web Site", 20),
                new ProspectStatistic("Word of Mouth", 25)
            )
        );

        // When
        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByContactOrigin();

        // Then
        assertEquals(expectedStatistics, actualStatistics);
    }
}