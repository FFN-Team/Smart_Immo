package com.gangdestrois.smartimmo.domain.portfolio;

import com.gangdestrois.smartimmo.domain.buyer.PropertiesFinder;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.model.PropertyPortfolio;
import com.gangdestrois.smartimmo.domain.portfolio.port.PropertiesToFollowApi;

import java.util.Date;

public class PropertiesToFollowManager implements PropertiesToFollowApi {
    private final PropertiesFinder propertiesFinder;
    private final BuyerSpi buyerSpi;

    public PropertiesToFollowManager(PropertiesFinder propertiesFinder, BuyerSpi buyerSpi) {
        this.propertiesFinder = propertiesFinder;
        this.buyerSpi = buyerSpi;
    }

    @Override
    public PropertyPortfolio createPortfolio(int buyerId){
        PropertyPortfolio propertyPortfolio =new PropertyPortfolio(new Date());
        propertyPortfolio.setBuyer(buyerSpi.findBuyerById(buyerId));
        propertyPortfolio.setPropertiesToFollow(propertiesFinder.findPropertiesForBuyer(buyerId));
        return propertyPortfolio;
    }
}
