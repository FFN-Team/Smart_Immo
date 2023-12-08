package com.gangdestrois.smartimmo.domain.portfolio.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.Date;
import java.util.List;

public class PortfolioPropertiesToFollow {
    private Date dateOfPortfolioGeneration;
    private Buyer buyer;
    private List<Property> propertiesToFollow;

    public PortfolioPropertiesToFollow(Date dateOfPortfolioGeneration) {
        this.dateOfPortfolioGeneration = dateOfPortfolioGeneration;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPropertiesToFollow(List<Property> propertiesToFollow) {
        this.propertiesToFollow = propertiesToFollow;
    }

    public Date getDateOfPortfolioGeneration() {
        return dateOfPortfolioGeneration;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<Property> getPropertiesToFollow() {
        return propertiesToFollow;
    }

}
