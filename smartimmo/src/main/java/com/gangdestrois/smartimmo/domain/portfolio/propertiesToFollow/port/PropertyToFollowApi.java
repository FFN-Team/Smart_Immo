package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port;

import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.enums.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;

import java.util.List;

public interface PropertyToFollowApi {
    List<PropertyToFollow> findAll();

    List<PropertyToFollow> findAllByBuyerId(Long buyerId);

    void updateStatusByPropertyToFollowId(Long propertyToFollowId, PropertyToFollowStatus status);

    void savePropertiesToFollowForBuyer(Long buyerId);
}