package com.gangdestrois.smartimmo.domain.buyer;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.buyer.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;

import java.util.ArrayList;
import java.util.List;

public class PropertiesFinder implements PropertiesFinderApi {
    private Buyer buyer;
    private List<Property> properties;
    private BuyerSpi buyerSpi;
    private PropertySpi propertySpi;

    public PropertiesFinder(BuyerSpi buyerSpi, PropertySpi propertySpi) {
        this.buyerSpi = buyerSpi;
        this.propertySpi = propertySpi;
        this.properties = propertySpi.findAll();
    }

    @Override
    public List<Property> findPropertiesForBuyer(int id){
        /*isNull*/
        if(buyerSpi.findBuyerById(id) == null) return null;
        /*else pas nécessaire*/
        else {
            this.properties = propertySpi.findAll(); //refresh properties
            this.buyer = buyerSpi.findBuyerById(id);
            List<Property> filtredProperties= new ArrayList<>();
            /*filteredProperties*/
            for(Property property : this.properties)
                /*stream + predicates avec filter */
                if(verifyAllCriterias(property)) filtredProperties.add(property);
            return filtredProperties;
        }
    }

    // faire des prédicats dans le même délire que les prédicats que j'ai fais pour les prospects
    private boolean verifyAllCriterias(Property property){
        return verifySurfaceCriteria(property) && verifyRoomsNumberCriteria(property);
    }

    private boolean verifySurfaceCriteria(Property property) {
        return this.buyer.getPropertyCriteria().minimumSurface()<= property.livableArea();
    }

    private boolean verifyRoomsNumberCriteria(Property property) {
        return this.buyer.getPropertyCriteria().roomsNumber()<= property.roomsNumber();
    }
}