package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.model.Property;

public class PropertyToFollow{
    private final Long id;
    private final Buyer buyer;
    private final Property property;
    private final String status; /*status = enum*/

    public PropertyToFollow(Long id, Buyer buyer, Property property, String status) {
        this.id = id;
        this.buyer = buyer;
        this.property = property;
        this.status = status;
    }

    public Long getId() { return id; }
    public Buyer getBuyer() { return buyer; }
    public Property getProperty() { return property; }
    public String getStatus() { return status; }
}
