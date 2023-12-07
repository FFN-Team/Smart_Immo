package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.acquereur.entite.Acquereur;
import com.gangdestrois.smartimmo.domain.bien.entite.Bien;
import com.gangdestrois.smartimmo.domain.portfolio.entite.PortfolioBienASuivre;

import java.util.Date;
import java.util.List;

public record PortfolioBASResponse(String intitule,Date dateGenerationPortfolio, Acquereur acquereur, List<Bien>biensASuivre) {
    public static  PortfolioBASResponse fromModel(PortfolioBienASuivre portfolioBienASuivre){
        return new PortfolioBASResponse(portfolioBienASuivre.getIntitule(),portfolioBienASuivre.getDateGenered(),
                portfolioBienASuivre.getAcquereur(), portfolioBienASuivre.getBiensASuivre());
    }
}
