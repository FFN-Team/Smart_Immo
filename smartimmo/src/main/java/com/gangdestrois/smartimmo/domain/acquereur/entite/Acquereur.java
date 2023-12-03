package com.gangdestrois.smartimmo.domain.acquereur.entite;

public class Acquereur {
    /* Prospect prospect,*/
    private Long id;
    private String statut;
    private String dateDebutRecherche;
    private String dateFinRecherche;
    private CriteresBien criteresBien;

    public Acquereur(Long id, String statut, String dateDebutRecherche, String dateFinRecherche,
                     CriteresBien criteresBien) {
        this.id = id;
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
}
