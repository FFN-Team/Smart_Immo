package com.gangdestrois.smartimmo.domain.acquereur.port;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;

import java.util.List;

public interface AcquereurSpi {
    List<Acquereur> findAllAcquereurs();
}
