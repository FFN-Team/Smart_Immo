package com.gangdestrois.smartimmo.domain.portfolio.entite;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.bien.entite.Bien;
import java.util.Date;
import java.util.List;

public class PortfolioBienASuivre {
    private static String intitule="Portfolio Biens À Suivre";
    private Date dateGenerationPortfolio;
    private Acquereur acquereur;
    private List<Bien> biensASuivre;

    public PortfolioBienASuivre(Date dateGenerationPortfolio) {
        this.dateGenerationPortfolio=dateGenerationPortfolio;
    }

    public void setAcquereur(Acquereur acquereur) {
        this.acquereur = acquereur;
    }

    public void setBiensASuivre(List<Bien> biensASuivre) {
        this.biensASuivre = biensASuivre;
    }

    public Date getDateGenered() {
        return dateGenerationPortfolio;
    }

    public Acquereur getAcquereur() {
        return acquereur;
    }

    public List<Bien> getBiensASuivre() {
        return biensASuivre;
    }

    public static String getIntitule() {
        return intitule;
    }
}
