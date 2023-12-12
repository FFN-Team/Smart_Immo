package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.buyer.BuyerManager;
import com.gangdestrois.smartimmo.domain.buyer.PropertiesFinder;
import com.gangdestrois.smartimmo.infrastructure.jpa.BuyerDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PropertyDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PropertyCriteriaRepository;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.portfolio.PortfolioPropertiesToFollowManager;
import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProjectManager;
import com.gangdestrois.smartimmo.domain.property.PropertyManager;
import com.gangdestrois.smartimmo.domain.prospect.ProspectAnalyzer;
import com.gangdestrois.smartimmo.infrastructure.jpa.*;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gangdestrois.smartimmo.infrastructure.jpa.repository")
public class BeanConfiguration {
    @Bean
    public PropertyDataAdapter propertyDataAdapter(PropertyRepository propertyRepository) {
        return new PropertyDataAdapter(propertyRepository);
    }

    @Bean
    public PropertyManager propertyManager(PropertyDataAdapter propertyDataAdapter) {
        return new PropertyManager(propertyDataAdapter);
    }

    @Bean
    public BuyerDataAdapter buyerDataAdapter(PropertyCriteriaRepository propertyCriteriaRepository) {
        return new BuyerDataAdapter(propertyCriteriaRepository);
    }

    @Bean
    public BuyerManager buyerManager(BuyerDataAdapter buyerDataAdapter) {
        return new BuyerManager(buyerDataAdapter);
    }

    @Bean
    public PropertiesFinder propertiesFinder(BuyerDataAdapter buyerDataAdapter, PropertyDataAdapter propertyDataAdapter) {
        return new PropertiesFinder(buyerDataAdapter, propertyDataAdapter);
    }

    @Bean
    public PortfolioPropertiesToFollowManager portfolioPropertiesToFollowManager(PropertiesFinder propertiesFinder,
                                                                                 BuyerDataAdapter buyerDataAdapter) {
        return new PortfolioPropertiesToFollowManager(propertiesFinder, buyerDataAdapter);
    }

    @Bean
    public PotentialProjectDataAdapter potentialProjectDataAdapter(PotentialProjectRepository potentialProjectRepository) {
        return new PotentialProjectDataAdapter(potentialProjectRepository);
    }

    @Bean
    public EventTypeNotificationDataAdapter eventTypeNotificationDataAdapter(EventTypeNotificationRepository eventTypeNotificationRepository,
                                                                             NotificationRepository notificationRepository) {
        return new EventTypeNotificationDataAdapter(eventTypeNotificationRepository, notificationRepository);
    }

    @Bean
    public NotificationAlertListener notificationAlertListener(EventTypeNotificationDataAdapter eventTypeNotificationDataAdapter) {
        return new NotificationAlertListener(eventTypeNotificationDataAdapter);
    }

    @Bean
    public SubscriptionDataAdapter subscriptionDataAdapter(SubscriptionRepository subscriptionRepository,
                                                           NotificationAlertListener notificationAlertListener) {
        return new SubscriptionDataAdapter(subscriptionRepository, notificationAlertListener);
    }

    @Bean
    public EventManager eventManager(SubscriptionDataAdapter subscriptionDataAdapter) {
        return new EventManager(subscriptionDataAdapter);
    }

    @Bean
    public NotificationDataAdapter notificationDataAdapter(NotificationRepository notificationRepository,
                                                           PotentialProjectRepository potentialProjectRepository,
                                                           ProspectRepository prospectRepository) {
        return new NotificationDataAdapter(notificationRepository, potentialProjectRepository, prospectRepository);
    }

    @Bean
    public PotentialProjectManager potentialProjectManager(PotentialProjectDataAdapter potentialProjectDataAdapter,
                                                           EventManager eventManager,
                                                           NotificationDataAdapter notificationDataAdapter) {
        return new PotentialProjectManager(potentialProjectDataAdapter, eventManager, notificationDataAdapter);
    }

    @Bean
    public ProspectDataAdapter prospectDataAdapter(ProspectRepository prospectRepository) {
        return new ProspectDataAdapter(prospectRepository);
    }

    @Bean
    public ProspectManager prospectManager(ProspectDataAdapter prospectDataAdapter){
        return new ProspectManager(prospectDataAdapter);
    }

    @Bean
    public ProspectAnalyzer prospectAnalyzer(ProspectDataAdapter prospectDataAdapter, NotificationDataAdapter notificationDataAdapter,
                                             EventManager eventManager) {
        return new ProspectAnalyzer(prospectDataAdapter, notificationDataAdapter, eventManager);
    }
}