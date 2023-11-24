package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.project.ProjetAnticipe;
import com.gangdestrois.smartimmo.domain.project.port.ProjetSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProjetAnticipeEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProjetAnticipeRepository;

import java.time.LocalDate;
import java.util.List;

public class ProjetAnticipeDataAdapter implements ProjetSpi {
    private final ProjetAnticipeRepository projetAnticipeRepository;

    public ProjetAnticipeDataAdapter(ProjetAnticipeRepository projetAnticipeRepository) {
        this.projetAnticipeRepository = projetAnticipeRepository;
    }

    @Override
    public List<ProjetAnticipe> findProjetsByDatePrevue(LocalDate date) {
        return projetAnticipeRepository.findByDatePrevue()
                .stream()
                .map(ProjetAnticipeEntity::toModel)
                .toList();
    }
}
