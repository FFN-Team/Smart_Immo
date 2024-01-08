package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.BuyerNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
    public void resetAndSavePropertiesToFollowForBuyer(Long buyerId) {

        if (isNull(buyerSpi.findBuyerById(buyerId)))
            throw new BuyerNotFoundException("Buyer not found with ID: " + buyerId);

        this.buyer = buyerSpi.findBuyerById(buyerId);
        propertyToFollowSpi.deletePropertiesToFollowForBuyer(buyerId);

        List<Property> filteredProperties = propertySpi.findAll().stream()
                .filter(PropertyCriteriaPredicates.allCriteriaPredicate(this.buyer))
                .peek(property -> propertyToFollowSpi.savePropertyToFollow(this.buyer, property))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatusByPropertyToFollowId(Long propertyToFollowId, PropertyToFollowStatus status) {
        propertyToFollowSpi.updateStatusByPropertyToFollowId(propertyToFollowId, status);
    }
}