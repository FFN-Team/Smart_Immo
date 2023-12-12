package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectDataResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

import java.util.*;

public class ProspectManager implements ProspectApi {
    ProspectSpi prospectSpi;

    public ProspectManager(ProspectSpi prospectSpi) {
        this.prospectSpi = prospectSpi;
    }

    @Override
    public List<Prospect> findAll() {
        return prospectSpi.findAll();
    }

    @Override
    public ProspectStatisticsResponse countByAgeGroup() {
        int[][] ageCategory = {
                { 0, 20 },
                { 21, 40 },
                { 41, 60 },
                { 61, 80 },
                { 81, 100 },
                { 100, 120 },
        };

        ProspectStatisticsResponse statisticsResponse;
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by age group", category;
        int ageMin, ageMax;
        long value;

        for (int[] ages : ageCategory) {
            ageMin = ages[0];
            ageMax = ages[1];
            category = String.format("%d - %d", ageMin, ageMax);
            value = prospectSpi.countByAgeBetween(ageMin, ageMax);

            dataResponses.add(new ProspectDataResponse(category, value));
        }

        statisticsResponse = new ProspectStatisticsResponse(
                title,
                dataResponses
        );

        return statisticsResponse;
    }

    @Override
    public ProspectStatisticsResponse countByProfession() {
        ProspectStatisticsResponse statisticsResponse;
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by profession";
        List<Object[]> data = prospectSpi.countByProfession();
        String profession;
        Long value;

        for (Object[] objects : data){
            profession = (String)objects[0];
            value = (Long)objects[1];

            dataResponses.add(new ProspectDataResponse(profession, value));
        }

        statisticsResponse = new ProspectStatisticsResponse(
            title,
            dataResponses
        );

        return statisticsResponse;
    }

    @Override
    public ProspectStatisticsResponse countByContactOrigin() {
        ProspectStatisticsResponse statisticsResponse;
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by contact origin";
        List<Object[]> data = prospectSpi.countByContactOrigin();
        String contactOrigin;
        Long value;

        for (Object[] objects : data){
            contactOrigin = (String)objects[0];
            value = (Long)objects[1];

            dataResponses.add(new ProspectDataResponse(contactOrigin, value));
        }

        statisticsResponse = new ProspectStatisticsResponse(
            title,
            dataResponses
        );

        return statisticsResponse;
    }
}
