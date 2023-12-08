package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProject;

import java.util.List;

public interface ProjectSpi {

    //Potential projects with no notifications or notifications state different of ARCHIVED or DEALT
    List<PotentialProject> findPotentialProjectsByNotificationToDisplay();

    List<PotentialProject> findPotentialProjectToNotify();
}
