package com.gangdestrois.smartimmo.domain.portfolio;

import com.gangdestrois.smartimmo.domain.buyer.PropertiesFinder;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerSpi;
import com.gangdestrois.smartimmo.domain.portfolio.model.PortfolioPropertiesToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.port.PortfolioPropertiesToFollowApi;

import java.util.Date;

/*est-ce que on pourrait pas plutôt appeler cette classe PropertiesToFollowManger
* et faire une méthode createPortfolio parce que là je trouve que les noms sont un peu tirés par les cheveux*/
public class PortfolioPropertiesToFollowManager implements PortfolioPropertiesToFollowApi {
    private PropertiesFinder propertiesFinder;
    private BuyerSpi buyerSpi;

    public PortfolioPropertiesToFollowManager(PropertiesFinder propertiesFinder, BuyerSpi buyerSpi) {
        this.propertiesFinder = propertiesFinder;
        this.buyerSpi = buyerSpi;
    }

    @Override
    //ce serait bien d'avoir un naming plus précis que id
    public PortfolioPropertiesToFollow createPortfolioPropertiesToFollowApi(int id){
        PortfolioPropertiesToFollow portfolioPropertiesToFollow =new PortfolioPropertiesToFollow(new Date());
        portfolioPropertiesToFollow.setBuyer(buyerSpi.findBuyerById(id));
        portfolioPropertiesToFollow.setPropertiesToFollow(propertiesFinder.findPropertiesForBuyer(id));
        return portfolioPropertiesToFollow;
    }
}
