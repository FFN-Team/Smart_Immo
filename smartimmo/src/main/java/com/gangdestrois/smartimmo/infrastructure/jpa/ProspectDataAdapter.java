package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.prospect.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;

import java.util.List;

public class ProspectDataAdapter implements ProspectSpi {
    private final ProspectRepository prospectRepository;

    public ProspectDataAdapter(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    @Override
    public List<Prospect> findAll() {
        return prospectRepository.findAll().stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public long countByAgeBetween(int ageMin, int ageMax){
        return prospectRepository.countByAgeBetween(ageMin, ageMax);
    }

    @Override
    public List<Object[]> countByProfession() {
        return prospectRepository.countByProfession();
    }

    public List<ProspectStatistic> countByProfessionExample() {
        return prospectRepository.countByProfession().stream()
                .map(prospectStatistic -> new ProspectStatistic((String)prospectStatistic[0], (Double)prospectStatistic[1]))
                .toList();
    }

    @Override
    public List<Object[]> countByContactOrigin() {
        return prospectRepository.countByContactOrigin();
    }

    public List<ProspectStatistic> countByContactOriginExample() {
        return prospectRepository.countByContactOrigin().stream()
                .map(prospectStatistic -> new ProspectStatistic((String)prospectStatistic[0], (Double)prospectStatistic[1]))
                .toList();
    }
}
