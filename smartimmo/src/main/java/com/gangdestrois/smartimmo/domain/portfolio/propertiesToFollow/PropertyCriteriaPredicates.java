package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.property.model.Property;

import java.util.Objects;
import java.util.function.Predicate;

public class PropertyCriteriaPredicates {
    public static Predicate<Property> allCriteriaPredicate(Buyer buyer) {
        return surfaceCriteriaPredicate(buyer).and(roomsNumberCriteriaPredicate(buyer));
    }

    public static Predicate<Property> surfaceCriteriaPredicate(Buyer buyer) {
        return property -> Objects.nonNull(buyer) &&
                Objects.nonNull(buyer.getPropertyCriteria()) &&
                buyer.getPropertyCriteria().minimumSurface() <= property.livableArea();
    }

    public static Predicate<Property> roomsNumberCriteriaPredicate(Buyer buyer) {
        return property -> Objects.nonNull(buyer) &&
                Objects.nonNull(buyer.getPropertyCriteria()) &&
                buyer.getPropertyCriteria().roomsNumber() <= property.roomsNumber();
    }
}
