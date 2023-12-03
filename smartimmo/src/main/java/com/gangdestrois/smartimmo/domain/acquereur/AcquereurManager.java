package com.gangdestrois.smartimmo.domain.acquereur;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurApi;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import java.util.List;

public class AcquereurManager implements AcquereurApi {
    AcquereurSpi acquereurSpi;

    public AcquereurManager(AcquereurSpi acquereurSpi) {
        this.acquereurSpi = acquereurSpi;
    }

    /* @Override
    public List<Acquereur> collectAllAcquereurs(){
        return acquereurSpi.findAllAcquereurs();
    }
    */
}
