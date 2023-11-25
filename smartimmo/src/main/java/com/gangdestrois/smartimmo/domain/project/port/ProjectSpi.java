package com.gangdestrois.smartimmo.domain.project.port;

import com.gangdestrois.smartimmo.domain.project.PotentialProject;

import java.time.LocalDate;
import java.util.List;

public interface ProjectSpi {
    List<PotentialProject> findProjectsByDueDate(LocalDate date);
}
