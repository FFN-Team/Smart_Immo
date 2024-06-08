package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.enums.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.model.Property;

public record PropertyToFollowResponse(Long id, Buyer buyer, Property property, PropertyToFollowStatus status) {
    public static PropertyToFollowResponse fromModel(PropertyToFollow propertyToFollow) {
        return new PropertyToFollowResponse(propertyToFollow.getId(), propertyToFollow.getBuyer(),
                propertyToFollow.getProperty(), propertyToFollow.getStatus());
    }
}
