package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.acquereur.entite.CriteresBien;

public record AcquereurResponse(Long id, String statut, String dateDebutRecherche, String dateFinRecherche,
                                CriteresBien criteresBien) {
    public static AcquereurResponse fromModel(Acquereur acquereur) {
        return new AcquereurResponse(acquereur.getId(), acquereur.getStatut(),
                acquereur.getDateDebutRecherche(), acquereur.getDateFinRecherche(),
                acquereur.getCriteresBienAcquereur());
    }
}
