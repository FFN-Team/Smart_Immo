package com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowSpi;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;

import java.util.Objects;
import java.util.function.Predicate;
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
        this.propertyToFollowSpi=propertyToFollowSpi;
    }

    @Override
    public List<PropertyToFollow> findAllByBuyerId(int buyerId) {
        return propertyToFollowSpi.findAllByBuyerId(buyerId);
    }

    @Override
    public List<Property> savePropertiesToFollowForBuyer(int buyerId){
        if (isNull(buyerSpi.findBuyerById(buyerId))) return null;

        // Vider les lignes pour le buyer dans la table PTF
        propertyToFollowSpi.deletePropertiesToFollowForBuyer(buyerId);

        List<Property> properties = propertySpi.findAll();
        this.buyer = buyerSpi.findBuyerById(buyerId);

        // Filtrer les propriétés et les enregistrer dans la table PTF
        List<Property> filteredProperties = properties.stream()
                .filter(allCriteriaPredicate())
                .peek(property -> propertyToFollowSpi.savePropertyToFollow(this.buyer, property))
                .collect(Collectors.toList());

        return filteredProperties;
    }

    private Predicate<Property> allCriteriaPredicate() {
        return property -> verifySurfaceCriteria().test(property) && verifyRoomsNumberCriteria().test(property);
    }

    private Predicate<Property> verifySurfaceCriteria() {
        return property -> Objects.nonNull(this.buyer) &&
                Objects.nonNull(this.buyer.getPropertyCriteria()) &&
                this.buyer.getPropertyCriteria().minimumSurface() <= property.livableArea();
    }

    private Predicate<Property> verifyRoomsNumberCriteria() {
        return property -> Objects.nonNull(this.buyer) &&
                Objects.nonNull(this.buyer.getPropertyCriteria()) &&
                this.buyer.getPropertyCriteria().roomsNumber() <= property.roomsNumber();
    }
}