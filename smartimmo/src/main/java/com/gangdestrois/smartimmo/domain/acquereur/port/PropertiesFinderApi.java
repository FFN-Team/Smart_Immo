package com.gangdestrois.smartimmo.domain.acquereur.port;

import com.gangdestrois.smartimmo.domain.bien.Bien;

import java.util.List;

///////////////// A mettre des methodes en anglais ///////////////////

public interface PropertiesFinderApi {
    public boolean verifyCritereSurface(Bien b/*, Acquereur a*/); //=> classe acquereur à créer
    public boolean verifyCriterePiece(Bien b/*, Acquereur a*/); //=> classe acquereur à créer
    public List<Bien> rechercherBiens();
}
