package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.enums.AgeCategory;
import com.gangdestrois.smartimmo.domain.prospect.model.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectStatisticsGeneratorApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectDataResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

import java.util.ArrayList;
import java.util.List;

public class ProspectStatisticsGenerator implements ProspectStatisticsGeneratorApi {
    private final ProspectSpi prospectSpi;

    public ProspectStatisticsGenerator(ProspectSpi prospectSpi) {
        this.prospectSpi = prospectSpi;
    }

    @Override
    public ProspectStatisticsResponse countByAgeGroup() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by age group", category;
        long value;

        for (AgeCategory ageCategory : AgeCategory.values()) {
            category = String.format("%d - %d", ageCategory.ageMin(), ageCategory.ageMax());
            value = prospectSpi.countByAgeBetween(ageCategory.ageMin(), ageCategory.ageMax());
            dataResponses.add(new ProspectDataResponse(category, value));
        }

        return new ProspectStatisticsResponse(
                title,
                dataResponses
        );
    }

    @Override
    public ProspectStatisticsResponse countByProfession() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by profession";
        var data = prospectSpi.countByProfession();
        return getProspectStatisticsResponse(dataResponses, title, data);
    }

    @Override
    public ProspectStatisticsResponse countByContactOrigin() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by contact origin";
        var data = prospectSpi.countByContactOrigin();
        return getProspectStatisticsResponse(dataResponses, title, data);
    }

    private ProspectStatisticsResponse getProspectStatisticsResponse(List<ProspectDataResponse> dataResponses,
                                                                     String title,
                                                                     List<ProspectStatistic> data) {
        for (ProspectStatistic prospectStatistic : data) {
            dataResponses.add(new ProspectDataResponse(
                    prospectStatistic.dataProspect(),
                    prospectStatistic.count()));
        }
        return new ProspectStatisticsResponse(
                title,
                dataResponses
        );
    }
}
