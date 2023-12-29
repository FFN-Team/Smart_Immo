package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port;

import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import java.util.List;

public interface PropertyToFollowApi {
    List<PropertyToFollow> findAll();
    List<PropertyToFollow> findAllByBuyerId(Long buyerId);
    List<Property> savePropertiesToFollowForBuyer(Long buyerId);
    void updateStatusByPropertyToFollowId(Long propertyToFollowId, String status);
}