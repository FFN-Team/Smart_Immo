package com.gangdestrois.smartimmo.domain.portfolio.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PortfolioPropertiesToFollow {
    private Date dateOfPortfolioGeneration;
    private Buyer buyer;
    private List<PropertyToFollow> propertiesToFollow;

    public PortfolioPropertiesToFollow(Date dateOfPortfolioGeneration) {
        propertiesToFollow= new ArrayList<>();
        this.dateOfPortfolioGeneration = dateOfPortfolioGeneration;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPropertiesToFollow(List<Property> properties) {
        for(Property property : properties){
            propertiesToFollow.add(new PropertyToFollow(property,"A Etudier"));
        }
    }

    public Date getDateOfPortfolioGeneration() {
        return dateOfPortfolioGeneration;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<PropertyToFollow> getPropertiesToFollow() {
        return propertiesToFollow;
    }

}
