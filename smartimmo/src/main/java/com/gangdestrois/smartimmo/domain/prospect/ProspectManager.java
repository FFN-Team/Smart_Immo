package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Map<String, Long>> countByAgeGroup() {
        int[][] ageCategory = {
                { 0, 20 },
                { 21, 40 },
                { 41, 60 },
                { 61, 80 },
                { 81, 100 },
                { 100, 120 },
        };
        Map<String, Map<String, Long>> countByAgeGroup = new HashMap<>();
        String title = "Number of prospects by age group", category;
        Map<String, Long> stats = new LinkedHashMap<>();
        int ageMin, ageMax;
        long value;

        for (int[] ages : ageCategory) {
            ageMin = ages[0];
            ageMax = ages[1];
            category = String.format("%d - %d", ageMin, ageMax);
            value = prospectSpi.countByAgeBetween(ageMin, ageMax);
            stats.put(category, value);
        }

        countByAgeGroup.put(title, stats);

        return countByAgeGroup;
    }

    @Override
    public Map<String, Map<String, Long>> countByProfession() {
        Map<String, Map<String, Long>> countByProfession = new HashMap<>();
        String title = "Number of prospects by profession";
        Map<String, Long> stats = new LinkedHashMap<>();
        List<Object[]> data = prospectSpi.countByProfession();
        String profession;
        Long value;

        for (Object[] objects : data){
            profession = (String)objects[0];
            value = (Long)objects[1];
            stats.put(profession, value);
        }

        countByProfession.put(title, stats);

        return countByProfession;
    }

    @Override
    public Map<String, Map<String, Long>> countByContactOrigin() {
        Map<String, Map<String, Long>> countByContactOrigin = new HashMap<>();
        String title = "Number of prospects by contact origin";
        Map<String, Long> stats = new LinkedHashMap<>();
        List<Object[]> data = prospectSpi.countByContactOrigin();
        String contactOrigin;
        Long value;

        for (Object[] objects : data){
            contactOrigin = (String)objects[0];
            value = (Long)objects[1];
            stats.put(contactOrigin, value);
        }

        countByContactOrigin.put(title, stats);

        return countByContactOrigin;
    }
}
