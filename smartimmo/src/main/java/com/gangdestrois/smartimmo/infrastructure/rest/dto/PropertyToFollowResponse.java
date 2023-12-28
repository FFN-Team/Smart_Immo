package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.entite.Property;

public record PropertyToFollowResponse(Long id, Buyer buyer, Property property, String state) {
    public static PropertyToFollowResponse fromModel(PropertyToFollow propertyToFollow){
        return new PropertyToFollowResponse(propertyToFollow.getId(), propertyToFollow.getBuyer(),
                propertyToFollow.getProperty(), propertyToFollow.getState());
    }
}
