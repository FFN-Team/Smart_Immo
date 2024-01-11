package com.gangdestrois.smartimmo.domain.filter.prospect;

import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.filter.prospect.port.ProspectFilterApi;
import com.gangdestrois.smartimmo.domain.filter.prospect.port.ProspectFilterSpi;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ProspectFilterManager implements ProspectFilterApi {
    ProspectSpi prospectSpi;
    ProspectFilterSpi prospectFilterSpi;

    public ProspectFilterManager(ProspectSpi prospectSpi, ProspectFilterSpi prospectFilterSpi) {
        this.prospectSpi = prospectSpi;
        this.prospectFilterSpi = prospectFilterSpi;
    }

    @Override
    public List<Prospect> filterProspects(ProspectFilter prospectFilter) {
        List<Prospect> finalFilteredProspects = new ArrayList<>(prospectSpi.findAll());

        if (nonNull(prospectFilter.getContactOrigin())) {
            finalFilteredProspects = intersectionByProspectId(finalFilteredProspects,
                    new ArrayList<>(prospectSpi.findAllByContactOrigin(prospectFilter.getContactOrigin())));
        }

        if (nonNull(prospectFilter.getTitle())) {
            finalFilteredProspects = intersectionByProspectId(finalFilteredProspects,
                    new ArrayList<>(prospectSpi.findAllByTitle(prospectFilter.getTitle())));
        }

        if (nonNull(prospectFilter.getAgeComparator()) && !isNull(prospectFilter.getAge())) {
            finalFilteredProspects = intersectionByProspectId(finalFilteredProspects,
                    new ArrayList<>(prospectSpi.findAllByAge(prospectFilter.getAge(), prospectFilter.getAgeComparator())));
        }

        if (nonNull(prospectFilter.getProfession())) {
            finalFilteredProspects = intersectionByProspectId(finalFilteredProspects,
                    new ArrayList<>(prospectSpi.findAllByProfession(prospectFilter.getProfession())));
        }

        if (nonNull(prospectFilter.isAuthorizeContactOnSocialMedia())) {
            finalFilteredProspects = intersectionByProspectId(finalFilteredProspects,
                    new ArrayList<>(prospectSpi.findAllByAuthorizeContactOnSocialMedia(prospectFilter.isAuthorizeContactOnSocialMedia())));
        }

        return finalFilteredProspects;
    }

    @Override
    public void saveProspectFilter(ProspectFilter prospectFilter) {
        if (prospectFilterSpi.existsAllByProspectFilterName(prospectFilter.getProspectFilterName())) {
            throw new DataIntegrityViolationException("Un enregistrement avec le nom " + prospectFilter.getProspectFilterName() + " existe déjà.");
        }
        prospectFilterSpi.saveProspectFilter(prospectFilter);
    }

    @Override
    public ProspectFilter findByProspectFilterName(String prospectFilterName) {
        return prospectFilterSpi.findByProspectFilterName(prospectFilterName);
    }

    @Override
    public List<ProspectFilter> findAll() {
        return prospectFilterSpi.findAll();
    }

    public List<Prospect> intersectionByProspectId(List<Prospect> list1, List<Prospect> list2) {
        return list1.stream()
                .map(Prospect::id)
                .filter(id -> list2.stream().anyMatch(prospect -> id.equals(prospect.id())))
                .map(id -> list2.stream().filter(prospect -> id.equals(prospect.id())).findFirst().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
