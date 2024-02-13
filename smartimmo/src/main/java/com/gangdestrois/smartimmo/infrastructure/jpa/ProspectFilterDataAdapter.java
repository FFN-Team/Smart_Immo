package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.filter.prospect.port.ProspectFilterSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectFilterEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectFilterRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;

public class ProspectFilterDataAdapter implements ProspectFilterSpi {
    private final ProspectFilterRepository prospectFilterRepository;

    public ProspectFilterDataAdapter(ProspectFilterRepository prospectFilterRepository) {
        this.prospectFilterRepository = prospectFilterRepository;
    }

    @Override
    @Transactional
    public void saveProspectFilter(ProspectFilter prospectFilter) {
        prospectFilterRepository.save(ProspectFilterEntity.fromModelToEntity(prospectFilter));
    }

    @Override
    public boolean existsAllByProspectFilterName(String prospectFilterName) {
        return prospectFilterRepository.existsAllByProspectFilterName(prospectFilterName);
    }

    @Override
    public ProspectFilter findByProspectFilterName(String prospectFilterName) {
        ProspectFilterEntity prospectFilterEntity = prospectFilterRepository.findByProspectFilterName(prospectFilterName);
        if (isNull(prospectFilterEntity)) {
            throw new NoSuchElementException("Aucun ProspectFilter avec le nom " + prospectFilterName + " n'a été trouvé.");
        }
        return prospectFilterEntity.toModel();
    }

    @Override
    @Transactional
    public Integer deleteByProspectFilterName(String prospectFilterName) {
        return prospectFilterRepository.deleteByProspectFilterName(prospectFilterName);
    }

    @Override
    public List<ProspectFilter> findAll() {
        return prospectFilterRepository.findAll()
                .stream()
                .map(ProspectFilterEntity::toModel)
                .toList();
    }


}
