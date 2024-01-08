package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.model.Property;

import java.util.List;

public interface PropertyToFollowSpi {
    List<PropertyToFollow> findAll();
    List<PropertyToFollow> findAllByBuyerId(Long buyerId);

    void savePropertyToFollow(Buyer buyer, Property property);

    void deletePropertiesToFollowForBuyer(Long buyerId);

    void updateStatusByPropertyToFollowId(Long propertyToFollowId, PropertyToFollowStatus status);
}
