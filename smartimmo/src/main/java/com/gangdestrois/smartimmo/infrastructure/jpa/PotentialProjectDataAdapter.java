package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Notify;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;

import java.time.LocalDate;
import java.util.List;

public class PotentialProjectDataAdapter implements PotentialProjectSpi {
    private final PotentialProjectRepository potentialProjectRepository;

    public PotentialProjectDataAdapter(PotentialProjectRepository potentialProjectRepository) {
        this.potentialProjectRepository = potentialProjectRepository;
    }

    @Override
    public List<Notify> findPotentialProjectToNotify() {
        return potentialProjectRepository.findByNotificationDate(LocalDate.now())
                .stream()
                .map(PotentialProjectEntity::toModel)
                .toList();
    }

    @Override
    public List<Notify> findPotentialProjectsByNotificationToDisplay() {
        return potentialProjectRepository.findPotentialProjectEntitiesByNotificationToDisplay()
                .stream()
                .map(PotentialProjectEntity::toModel)
                .toList();
    }
}
