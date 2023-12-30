package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.buyer.BuyerManager;
import com.gangdestrois.smartimmo.domain.email.EmailManager;
import com.gangdestrois.smartimmo.domain.email.GmailSender;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.event.NotificationManager;
import com.gangdestrois.smartimmo.domain.filter.prospect.ProspectFilterManager;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.PropertiesToFollowManager;
import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProjectManager;
import com.gangdestrois.smartimmo.domain.property.AddressManager;
import com.gangdestrois.smartimmo.domain.property.PropertyManager;
import com.gangdestrois.smartimmo.domain.prospect.ProspectAnalyzer;
import com.gangdestrois.smartimmo.domain.prospect.ProspectManager;
import com.gangdestrois.smartimmo.domain.prospect.ProspectStatisticsGenerator;
import com.gangdestrois.smartimmo.infrastructure.jpa.*;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.spring6.SpringTemplateEngine;

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
    public PropertyToFollowDataAdapter propertyToFollowDataAdapter(PropertyToFollowRepository propertyToFollowRepository) {
        return new PropertyToFollowDataAdapter(propertyToFollowRepository);
    }

    @Bean
    public BuyerManager buyerManager(BuyerDataAdapter buyerDataAdapter) {
        return new BuyerManager(buyerDataAdapter);
    }

    @Bean
    public PropertiesToFollowManager propertiesToFollowManager(BuyerDataAdapter buyerDataAdapter,
                                                               PropertyDataAdapter propertyDataAdapter, PropertyToFollowDataAdapter propertyToFollowDataAdapter) {
        return new PropertiesToFollowManager(buyerDataAdapter, propertyDataAdapter,propertyToFollowDataAdapter);
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
    public NotificationManager notificationManager(NotificationDataAdapter notificationDataAdapter) {
        return new NotificationManager(notificationDataAdapter);
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
    public ProspectAnalyzer prospectAnalyzer(ProspectDataAdapter prospectDataAdapter, NotificationDataAdapter notificationDataAdapter,
                                             EventManager eventManager) {
        return new ProspectAnalyzer(prospectDataAdapter, notificationDataAdapter, eventManager);
    }

    @Bean
    public ProspectManager prospectManager(ProspectDataAdapter prospectDataAdapter){
        return new ProspectManager(prospectDataAdapter);
    }

    @Bean
    public ProspectStatisticsGenerator prospectStatisticsGenerator(ProspectDataAdapter prospectDataAdapter) {
        return new ProspectStatisticsGenerator(prospectDataAdapter);
    }

    @Bean
    public AddressDataAdapter addressDataAdapter(AddressRepository addressRepository) {
        return new AddressDataAdapter(addressRepository);
    }

    @Bean
    public AddressManager addressManager(AddressDataAdapter addressDataAdapter) {
        return new AddressManager(addressDataAdapter);
    }

    @Bean
    public GmailSender gmailSender() throws Exception {
        return new GmailSender();
    }

    @Bean
    public EmailManager emailManager(SpringTemplateEngine thymeleafTemplateEngine, GmailSender gmailSender) {
        return new EmailManager(thymeleafTemplateEngine, gmailSender);
    }

    @Bean
    ProspectFilterDataAdapter prospectFilterDataAdapter(ProspectFilterRepository prospectFilterRepository){
        return new ProspectFilterDataAdapter(prospectFilterRepository);
    }
    @Bean
    public ProspectFilterManager prospectFilterManager(ProspectDataAdapter prospectDataAdapter,
                                                       ProspectFilterDataAdapter prospectFilterDataAdapter){
        return new ProspectFilterManager(prospectDataAdapter,prospectFilterDataAdapter);
    }
}