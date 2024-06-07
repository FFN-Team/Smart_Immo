package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.enums.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.property.model.Property;

public class PropertyToFollow {
    private final Long id;
    private final Buyer buyer;
    private final Property property;
    private final PropertyToFollowStatus status;

    public PropertyToFollow(Long id, Buyer buyer, Property property, PropertyToFollowStatus status) {
        this.id = id;
        this.buyer = buyer;
        this.property = property;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Property getProperty() {
        return property;
    }

    public PropertyToFollowStatus getStatus() {
        return status;
    }
}
