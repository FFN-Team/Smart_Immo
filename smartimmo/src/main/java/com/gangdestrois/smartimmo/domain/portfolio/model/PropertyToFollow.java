package com.gangdestrois.smartimmo.domain.portfolio.model;

import com.gangdestrois.smartimmo.domain.property.entite.Property;

/*stateToFollow = enum*/
public record PropertyToFollow(Property property, String stateToFollow){
}
