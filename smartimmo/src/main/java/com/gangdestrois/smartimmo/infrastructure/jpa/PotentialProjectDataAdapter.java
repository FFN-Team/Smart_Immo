package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.project.PotentialProject;
import com.gangdestrois.smartimmo.domain.project.port.ProjectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.PotentialProjectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;

import java.time.LocalDate;
import java.util.List;

public class PotentialProjectDataAdapter implements ProjectSpi {
    private final PotentialProjectRepository potentialProjectRepository;

    public PotentialProjectDataAdapter(PotentialProjectRepository potentialProjectRepository) {
        this.potentialProjectRepository = potentialProjectRepository;
    }

    @Override
    public List<PotentialProject> findPotentialProjectsByDueDate(LocalDate date) {
        return potentialProjectRepository.findByDatePrevue(date)
                .stream()
                .map(PotentialProjectEntity::toModel)
                .toList();
    }
}
