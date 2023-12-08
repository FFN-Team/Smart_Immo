package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.model.PortfolioPropertiesToFollow;

import java.util.Date;
import java.util.List;

public record PortfolioPTFResponse(String intitule, Date dateGenerationPortfolio, Buyer buyer, List<Property>biensASuivre) {
    public static PortfolioPTFResponse fromModel(PortfolioPropertiesToFollow portfolioPropertiesToFollow){
        return new PortfolioPTFResponse("Portfolio Biens À Suivre", portfolioPropertiesToFollow.getDateOfPortfolioGeneration(),
                portfolioPropertiesToFollow.getBuyer(), portfolioPropertiesToFollow.getPropertiesToFollow());
    }
}
