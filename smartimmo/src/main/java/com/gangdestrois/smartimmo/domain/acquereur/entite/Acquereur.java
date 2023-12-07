package com.gangdestrois.smartimmo.domain.acquereur.entite;

import com.gangdestrois.smartimmo.domain.prospect.entite.Prospect;

public class Acquereur {
    private Long id;
    private Prospect prospect;
    private String statut;
    private String dateDebutRecherche;
    private String dateFinRecherche;
    private CriteresBien criteresBien;

    public Acquereur(Long id, Prospect prospect, String statut, String dateDebutRecherche, String dateFinRecherche,
                     CriteresBien criteresBien) {
        this.id = id;
        this.prospect=prospect;
        this.statut = statut;
        this.dateDebutRecherche = dateDebutRecherche;
        this.dateFinRecherche = dateFinRecherche;
        this.criteresBien = criteresBien;
    }


    public Long getId() { return id; }
    public String getStatut() { return statut; }
    public String getDateDebutRecherche() { return dateDebutRecherche; }
    public String getDateFinRecherche() { return dateFinRecherche; }
    public CriteresBien getCriteresBienAcquereur() { return criteresBien; }
    public Prospect getProspect() { return prospect; }
}
