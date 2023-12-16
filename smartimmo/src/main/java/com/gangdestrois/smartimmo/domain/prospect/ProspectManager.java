package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectManagerApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectDataResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

import java.util.ArrayList;
import java.util.List;

//ici c'est pas vraiment un maneger parce qu'il ne manage pas vraiment c'est plutôt une classe qui fais des statistiques
//ce serait bien de trouver un nom qui correspond plus à ca
public class ProspectManager implements ProspectManagerApi {
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
        //on peut faire un enum ageCategory avec 2 attributs ageMin, ageMax
        int[][] ageCategory = {
                { 0, 20 },
                { 21, 40 },
                { 41, 60 },
                { 61, 80 },
                { 81, 100 },
                { 101, 120 },
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
        //il faudrait que Object[] soit transformé en object du domaine dans l'adapter
        //car Object[] n'est pas explicite et ne correspond pas vraiment à une donnée métier
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

    public ProspectStatisticsResponse countByAgeGroupExample() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        String title = "Number of prospects by age group", category;
        long value;

        for (AgeCategory ageCategory : AgeCategory.values()) {
            category = String.format("%d - %d", ageCategory.ageMin, ageCategory.ageMax);
            value = prospectSpi.countByAgeBetween(ageCategory.ageMin, ageCategory.ageMax);
            dataResponses.add(new ProspectDataResponse(category, value));
        }
        return new ProspectStatisticsResponse(
                title,
                dataResponses
        );
    }

    public ProspectStatisticsResponse countByProfessionExample() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        //est-ce que on pourrait pas mettre le title simplement dans le front ?
        String title = "Number of prospects by profession";
        var data = prospectSpi.countByProfessionExample();
        return getProspectStatisticsResponse(dataResponses, title, data);
    }

    public ProspectStatisticsResponse countByContactOriginExample() {
        List<ProspectDataResponse> dataResponses = new ArrayList<>();
        //est-ce que on pourrait pas mettre le title simplement dans le front ?
        String title = "Number of prospects by contact origin";
        var data = prospectSpi.countByContactOriginExample();
        return getProspectStatisticsResponse(dataResponses, title, data);
    }

    private ProspectStatisticsResponse getProspectStatisticsResponse(List<ProspectDataResponse> dataResponses,
                                                                     String title,
                                                                     List<ProspectStatistic> data) {
        for (ProspectStatistic prospectStatistic : data){
            dataResponses.add(new ProspectDataResponse(
                    prospectStatistic.dataProspect(),
                    prospectStatistic.count().longValue()));
        }
        return new ProspectStatisticsResponse(
                title,
                dataResponses
        );
    }
}
