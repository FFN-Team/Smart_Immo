package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.entite.CriteresBien;
import com.gangdestrois.smartimmo.domain.prospect.entite.Prospect;

public record AcquereurResponse(Long id, Prospect prospect, String statut, String dateDebutRecherche, String dateFinRecherche,
                                CriteresBien criteresBien) {
    public static AcquereurResponse fromModel(Acquereur acquereur) {
        return new AcquereurResponse(acquereur.getId(),acquereur.getProspect(),acquereur.getStatut(),
                acquereur.getDateDebutRecherche(), acquereur.getDateFinRecherche(),
                acquereur.getCriteresBienAcquereur());
    }
}
