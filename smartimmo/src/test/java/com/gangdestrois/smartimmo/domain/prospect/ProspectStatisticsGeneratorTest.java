package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.filter.prospect.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectDataResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProspectStatisticsGeneratorTest {
    @MockBean
    private ProspectStatisticsGenerator prospectStatisticsGenerator;

    class ProspectSpiMock implements ProspectSpi {
        @Override
        public List<Prospect> findAll() {
            return null;
        }

        @Override
        public long countByAgeBetween(int ageMin, int ageMax) {
            if (ageMin == 0) return 5;
            else if (ageMin == 21) return 10;
            else if (ageMin == 41) return 15;
            else if (ageMin == 61) return 20;
            else return 25;
        }

        @Override
        public List<ProspectStatistic> countByProfession() {
            return List.of(
                new ProspectStatistic("Doctor", 5),
                new ProspectStatistic("Engineer", 10),
                new ProspectStatistic("Teacher", 15),
                new ProspectStatistic("Student", 20),
                new ProspectStatistic("Commercial", 25)
            );
        }

        @Override
        public List<ProspectStatistic> countByContactOrigin() {
            return List.of(
                new ProspectStatistic("Email", 5),
                new ProspectStatistic("Phone", 10),
                new ProspectStatistic("Social Media", 15),
                new ProspectStatistic("Web Site", 20),
                new ProspectStatistic("Word of Mouth", 25)
            );
        }

        @Override
        public Optional<Prospect> findById(Long prospectId) {
            return Optional.empty();
        }

        @Override
        public List<Prospect> findAllByAge(Integer age, MathematicalComparator ageComparator) {
            return null;
        }

        @Override
        public List<Prospect> findAllByContactOrigin(ContactOrigin contactOrigin) {
            return null;
        }

        @Override
        public List<Prospect> findAllByTitle(Title title) {
            return null;
        }

        @Override
        public List<Prospect> findAllByProfession(Profession profession) {
            return null;
        }

        @Override
        public List<Prospect> findAllByAuthorizeContactOnSocialMedia(Boolean authorizeContactOnSocialMedia) {
            return null;
        }
    }

    @BeforeEach
    void setUp() {
        prospectStatisticsGenerator = new ProspectStatisticsGenerator(new ProspectSpiMock());
    }

    @Test
    void countByAgeGroup() {
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

        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByAgeGroup();

        assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    void countByProfession() {
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

        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByProfession();

        assertEquals(expectedStatistics, actualStatistics);
    }

    @Test
    void countByContactOrigin() {
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

        ProspectStatisticsResponse actualStatistics = prospectStatisticsGenerator.countByContactOrigin();

        assertEquals(expectedStatistics, actualStatistics);
    }
}