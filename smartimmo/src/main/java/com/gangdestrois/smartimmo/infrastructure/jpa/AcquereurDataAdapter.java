package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.CriteresBienAcquereurEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.CriteresBienAcquereurRepository;

import java.util.List;

public class AcquereurDataAdapter implements AcquereurSpi {
    private final CriteresBienAcquereurRepository criteresBienAcquereurRepository;

    public AcquereurDataAdapter(CriteresBienAcquereurRepository criteresBienAcquereurRepository) {
        this.criteresBienAcquereurRepository = criteresBienAcquereurRepository;
    }

    @Override
    public List<Acquereur> findAllAcquereurs() {
        return criteresBienAcquereurRepository.findAll()
                .stream()
                .map(CriteresBienAcquereurEntity::toAcquereurModel)
                .toList();
    }

    @Override
    public Acquereur findAcquereurById(int id) {
        CriteresBienAcquereurEntity criteresBienAcquereur = criteresBienAcquereurRepository
                .findCriteresBienAcquereurEntityByAcquereur_Id(id);
        if(criteresBienAcquereur != null) return criteresBienAcquereur.toAcquereurModel();
        else return null;
    }
}