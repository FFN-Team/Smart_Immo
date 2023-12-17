package com.gangdestrois.smartimmo.domain.portfolio.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PortfolioPropertiesToFollow /*PropertyPortfolio pas suffisant?*/ {
    /*Mettre des LocalDate plutôt que des Date*/
    private Date dateOfPortfolioGeneration;
    private Buyer buyer;
    private List<PropertyToFollow> propertiesToFollow;

    public PortfolioPropertiesToFollow(Date dateOfPortfolioGeneration) {
        /*this.propertiesToFollow*/
        propertiesToFollow= new ArrayList<>();
        this.dateOfPortfolioGeneration = dateOfPortfolioGeneration;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPropertiesToFollow(List<Property> properties) {
        for(Property property : properties){
            /*Attention pas de magic string, il faut faire un enum stateToFollow avec la valeur TO_STUDY */
            //situ veux absolumentun string il faut mettre TO_STUDY.name()
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
