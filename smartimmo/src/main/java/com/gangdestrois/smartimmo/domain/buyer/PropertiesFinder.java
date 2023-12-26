package com.gangdestrois.smartimmo.domain.buyer;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.buyer.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertySpi;

import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class PropertiesFinder implements PropertiesFinderApi {
    private Buyer buyer;
    private List<Property> properties;
    private final BuyerSpi buyerSpi;
    private final PropertySpi propertySpi;

    public PropertiesFinder(BuyerSpi buyerSpi, PropertySpi propertySpi) {
        this.buyerSpi = buyerSpi;
        this.propertySpi = propertySpi;
        this.properties = propertySpi.findAll();
    }

    @Override
    public List<Property> findPropertiesForBuyer(int id){
        if (isNull(buyerSpi.findBuyerById(id))) return null;

        this.properties = propertySpi.findAll(); // refresh properties
        this.buyer = buyerSpi.findBuyerById(id);
        List<Property> filteredProperties = this.properties.stream()
                .filter(allCriteriaPredicate())
                .collect(Collectors.toList());
        return filteredProperties;
    }

    private Predicate<Property> allCriteriaPredicate() {
        return property -> verifySurfaceCriteria(property) && verifyRoomsNumberCriteria(property);
    }

    private boolean verifySurfaceCriteria(Property property) {
        return this.buyer.getPropertyCriteria().minimumSurface() <= property.livableArea();
    }

    private boolean verifyRoomsNumberCriteria(Property property) {
        return this.buyer.getPropertyCriteria().roomsNumber() <= property.roomsNumber();
    }
}