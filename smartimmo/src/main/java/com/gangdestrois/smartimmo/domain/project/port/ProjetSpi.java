package com.gangdestrois.smartimmo.domain.project.port;

import com.gangdestrois.smartimmo.domain.project.ProjetAnticipe;

import java.time.LocalDate;
import java.util.List;

public interface ProjetSpi {
    List<ProjetAnticipe> findProjetsByDatePrevue(LocalDate date);
}
