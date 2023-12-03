package com.gangdestrois.smartimmo.domain.acquereur.port;

import com.gangdestrois.smartimmo.domain.bien.Bien;

import java.util.List;

///////////////// A mettre des methodes en anglais ///////////////////

public interface PropertiesFinderApi {
    List<Bien> findPropertiesForBuyer(int id);
    boolean verifyAllCriterias(Bien bien);
    boolean verifyCritereSurface(Bien bien);
    boolean verifyCritereNbPiece(Bien bien);
}
