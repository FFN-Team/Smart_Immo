package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProject;

import java.time.LocalDate;
import java.util.List;

public interface ProjectSpi {
    List<PotentialProject> findPotentialProjectsByDueDate(LocalDate date);
}
