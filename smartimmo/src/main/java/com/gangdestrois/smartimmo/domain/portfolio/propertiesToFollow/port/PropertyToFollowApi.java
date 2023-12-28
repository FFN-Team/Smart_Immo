package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port;

import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import java.util.List;

public interface PropertyToFollowApi {
    List<PropertyToFollow> findAllByBuyerId(int buyerId);
    List<Property> savePropertiesToFollowForBuyer(int buyerId);
}