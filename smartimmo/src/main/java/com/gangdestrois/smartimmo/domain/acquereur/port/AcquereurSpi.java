package com.gangdestrois.smartimmo.domain.acquereur.port;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.prospect.entite.Prospect;

import java.util.List;

public interface AcquereurSpi {
    List<Acquereur> findAllAcquereurs();
    Acquereur findAcquereurById(int id);
}
