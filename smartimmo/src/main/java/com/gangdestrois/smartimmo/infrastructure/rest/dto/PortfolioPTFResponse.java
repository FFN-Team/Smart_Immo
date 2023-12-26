package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.portfolio.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.model.PropertyPortfolio;

import java.util.Date;
import java.util.List;

public record PortfolioPTFResponse(String title, Date dateGenerationPortfolio, Buyer buyer,
                                   List<PropertyToFollow> propertiesToFollow) {
    public static PortfolioPTFResponse fromModel(PropertyPortfolio propertyPortfolio){
        return new PortfolioPTFResponse("Portfolio Properties To Follow", propertyPortfolio.getDateOfPortfolioGeneration(),
                propertyPortfolio.getBuyer(), propertyPortfolio.getPropertiesToFollow());
    }
}
