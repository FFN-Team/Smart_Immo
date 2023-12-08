package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.buyer.BuyerManager;
import com.gangdestrois.smartimmo.domain.buyer.PropertiesFinder;
import com.gangdestrois.smartimmo.domain.property.PropertyManager;
import com.gangdestrois.smartimmo.domain.portfolio.PortfolioPropertiesToFollowManager;
import com.gangdestrois.smartimmo.infrastructure.jpa.BuyerDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyCriteriaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PropertyDataAdapter propertyDataAdapter(PropertyRepository propertyRepository) {
        return new PropertyDataAdapter(propertyRepository);
    }

    @Bean
     public PropertyManager propertyManager(PropertyDataAdapter propertyDataAdapter){
        return new PropertyManager(propertyDataAdapter);
     }

    @Bean
    public BuyerDataAdapter buyerDataAdapter(PropertyCriteriaRepository propertyCriteriaRepository)
    {return new BuyerDataAdapter(propertyCriteriaRepository);}

    @Bean
    public BuyerManager buyerManager(BuyerDataAdapter buyerDataAdapter){
        return new BuyerManager(buyerDataAdapter);
    }

    @Bean
    public PropertiesFinder propertiesFinder(BuyerDataAdapter buyerDataAdapter, PropertyDataAdapter propertyDataAdapter){
        return new PropertiesFinder(buyerDataAdapter,propertyDataAdapter);
    }

    @Bean
    public PortfolioPropertiesToFollowManager portfolioPropertiesToFollowManager(PropertiesFinder propertiesFinder,
                                                                          BuyerDataAdapter buyerDataAdapter){
        return new PortfolioPropertiesToFollowManager(propertiesFinder,buyerDataAdapter);
    }
}