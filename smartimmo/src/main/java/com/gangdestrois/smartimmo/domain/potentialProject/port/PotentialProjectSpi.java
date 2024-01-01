package com.gangdestrois.smartimmo.domain.potentialProject.port;

import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;

import java.util.List;

public interface PotentialProjectSpi {

    //Potential projects with no notifications or notifications status different of ARCHIVED or DEALT
    List<PotentialProject> findPotentialProjectsByNotificationToDisplay();

    List<PotentialProject> findPotentialProjectToNotify();
}
