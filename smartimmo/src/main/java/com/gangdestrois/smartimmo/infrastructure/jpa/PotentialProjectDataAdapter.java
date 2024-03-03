package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.potentialProject.port.PotentialProjectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PotentialProjectDataAdapter implements PotentialProjectSpi {
    private final PotentialProjectRepository potentialProjectRepository;

    @Autowired
    public PotentialProjectDataAdapter(PotentialProjectRepository potentialProjectRepository) {
        this.potentialProjectRepository = potentialProjectRepository;
    }

    @Override
    public List<PotentialProject> findPotentialProjectToNotify() {
        return potentialProjectRepository.findByNotificationDate(LocalDate.now())
                .stream()
                .map(PotentialProjectEntity::toModel)
                .toList();
    }

    @Override
    public List<PotentialProject> findPotentialProjectsByNotificationToDisplay() {
        return potentialProjectRepository.findPotentialProjectEntitiesByNotificationToDisplay()
                .stream()
                .map(PotentialProjectEntity::toModel)
                .toList();
    }
}
