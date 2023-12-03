package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.CriteresBienAcquereurEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.CriteresBienAcquereurRepository;

import java.util.List;

public class AcquereurDataAdapter implements AcquereurSpi {
    private final CriteresBienAcquereurRepository acquereurCriteresBienRepository;

    public AcquereurDataAdapter(CriteresBienAcquereurRepository acquereurCriteresBienRepository) {
        this.acquereurCriteresBienRepository=acquereurCriteresBienRepository;
    }

    @Override
    public List<Acquereur> findAllAcquereurs() {
        return acquereurCriteresBienRepository.findAll()
                .stream()
                .map(CriteresBienAcquereurEntity::toAcquereurModel)
                .toList();
    }
}