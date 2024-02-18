package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;

import java.util.List;

import static java.util.Objects.isNull;

public class PropertiesToFollowManager implements PropertyToFollowApi {
    private Buyer buyer;
    private final BuyerSpi buyerSpi;
    private final PropertySpi propertySpi;
    private final PropertyToFollowSpi propertyToFollowSpi;

    public PropertiesToFollowManager(BuyerSpi buyerSpi, PropertySpi propertySpi, PropertyToFollowSpi propertyToFollowSpi) {
        this.buyerSpi = buyerSpi;
        this.propertySpi = propertySpi;
        this.propertyToFollowSpi = propertyToFollowSpi;
    }

    @Override
    public List<PropertyToFollow> findAll() {
        return propertyToFollowSpi.findAll();
    }

    @Override
    public List<PropertyToFollow> findAllByBuyerId(Long buyerId) {
        return propertyToFollowSpi.findAllByBuyerId(buyerId);
    }

    @Override
    public void updateStatusByPropertyToFollowId(Long propertyToFollowId, PropertyToFollowStatus status) {
        propertyToFollowSpi.updateStatusByPropertyToFollowId(propertyToFollowId, status);
    }

    @Override
    public void savePropertiesToFollowForBuyer(Long buyerId) {
        if (isNull(buyerSpi.findBuyerById(buyerId)))
            throw new NotFoundException(ExceptionEnum.BUYER_NOT_FOUND, "Buyer not found with ID: " + buyerId);

        this.buyer = buyerSpi.findBuyerById(buyerId);

        List<Property> filteredProperties = propertySpi.findAll().stream()
                .filter(PropertyCriteriaPredicates.allCriteriaPredicate(this.buyer))
                .toList();

        List<Property> existingProperties =  propertyToFollowSpi.findAllByBuyerId(buyerId)
                .stream()
                .map(x->x.getProperty())
                .toList();

        List<Property> notMatchingPropertiesForBuyer = existingProperties.stream()
                .filter(property -> !filteredProperties.contains(property))
                .peek(property -> propertyToFollowSpi.deletePropertyToFollowForBuyer(buyerId, property.id()))
                .toList();

        List<Property> newMatchingPropertiesForBuyer = filteredProperties.stream()
                .filter(property -> !existingProperties.contains(property))
                .peek(property -> propertyToFollowSpi.savePropertyToFollowForBuyer(this.buyer,property))
                .toList();
    }
}