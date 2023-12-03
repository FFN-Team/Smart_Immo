package com.gangdestrois.smartimmo.domain.acquereur;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.domain.acquereur.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.bien.Bien;
import com.gangdestrois.smartimmo.domain.bien.port.BienSpi;
import java.util.ArrayList;
import java.util.List;

public class PropertiesFinder implements PropertiesFinderApi {
    private Acquereur acquereur;
    private List<Bien> biens;
    private AcquereurSpi acquereurSpi;
    private BienSpi bienSpi;

    public PropertiesFinder(AcquereurSpi acquereurSpi, BienSpi bienSpi) {
        this.acquereurSpi = acquereurSpi;
        this.bienSpi = bienSpi;
        this.biens = bienSpi.findAll();
    }

    @Override
    public List<Bien> findPropertiesForBuyer(int id){
        if(acquereurSpi.findAcquereurById(id) == null) return null;
        else {
            this.acquereur=acquereurSpi.findAcquereurById(id);
            List<Bien> biensFiltred= new ArrayList<>();
            for(Bien bien : this.biens) if(verifyAllCriterias(bien)) biensFiltred.add(bien);
            return biensFiltred;
        }
    }

    @Override
    public boolean verifyAllCriterias(Bien bien){
        return verifyCritereSurface(bien) && verifyCritereNbPiece(bien);
    }

    @Override
    public boolean verifyCritereSurface(Bien bien) {
        return this.acquereur.getCriteresBienAcquereur().surfaceMinimum()<=bien.surfaceHabitable();
    }

    @Override
    public boolean verifyCritereNbPiece(Bien bien) {
        return this.acquereur.getCriteresBienAcquereur().nombrePiece()<=bien.nbPiece();
    }

}
