package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

public class PropertyToFollow{
    private final Long id;
    private final Buyer buyer;
    private final Property property;
    private final String state; /*state = enum*/

    public PropertyToFollow(Long id, Buyer buyer, Property property, String state) {
        this.id = id;
        this.buyer = buyer;
        this.property = property;
        this.state = state;
    }

    public Long getId() { return id; }
    public Buyer getBuyer() { return buyer; }
    public Property getProperty() { return property; }
    public String getState() { return state; }
}
