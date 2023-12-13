package com.gangdestrois.smartimmo.infrastructure.jpa;

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

    @Override
    public List<Object[]> countByContactOrigin() {
        return prospectRepository.countByContactOrigin();
    }

/*    @Override
    public List<Prospect> findProspectsThatMayExpandTheirFamily(MaritalStatus maritalStatus, Integer yearsSinceBuy,
                                                                Integer roomNumberOfProperty) {
        return prospectRepository.findProspectsThatMayExpandTheirFamily(maritalStatus, yearsSinceBuy, roomNumberOfProperty)
                .stream().map(ProspectEntity::toModel).toList();
    }*/
}
