package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.buyer.BuyerManager;
import com.gangdestrois.smartimmo.domain.document.DocumentManager;
import com.gangdestrois.smartimmo.domain.email.EmailManager;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.filter.prospect.ProspectFilterManager;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.PropertiesToFollowManager;
import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProjectManager;
import com.gangdestrois.smartimmo.domain.property.AddressManager;
import com.gangdestrois.smartimmo.domain.property.PropertyManager;
import com.gangdestrois.smartimmo.domain.prospect.ProspectAnalyzer;
import com.gangdestrois.smartimmo.domain.prospect.ProspectManager;
import com.gangdestrois.smartimmo.domain.prospect.ProspectStatisticsGenerator;
import com.gangdestrois.smartimmo.infrastructure.jpa.*;
import com.gangdestrois.smartimmo.infrastructure.service.GmailApi;
import com.gangdestrois.smartimmo.infrastructure.service.GoogleDriveApi;
import com.gangdestrois.smartimmo.infrastructure.service.ThymeleafConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static java.util.Objects.nonNull;

@Configuration
@EnableJpaRepositories(basePackages = "com.gangdestrois.smartimmo.infrastructure.jpa.repository")
@EnableConfigurationProperties
public class BeanConfiguration {

    @Bean
    public PropertyManager propertyManager(PropertyDataAdapter propertyDataAdapter) {
        return new PropertyManager(propertyDataAdapter);
    }

    @Bean
    public BuyerManager buyerManager(BuyerDataAdapter buyerDataAdapter) {
        return new BuyerManager(buyerDataAdapter);
    }

    @Bean
    public PropertiesToFollowManager propertiesToFollowManager(BuyerDataAdapter buyerDataAdapter,
                                                               PropertyDataAdapter propertyDataAdapter, PropertyToFollowDataAdapter propertyToFollowDataAdapter) {
        return new PropertiesToFollowManager(buyerDataAdapter, propertyDataAdapter, propertyToFollowDataAdapter);
    }

    @Bean
    public NotificationAlertListener notificationAlertListener(EventTypeNotificationDataAdapter eventTypeNotificationDataAdapter) {
        return new NotificationAlertListener(eventTypeNotificationDataAdapter);
    }

    @Bean
    public EventManager eventManager(SubscriptionDataAdapter subscriptionDataAdapter, NotificationDataAdapter notificationDataAdapter) {
        return new EventManager(subscriptionDataAdapter, notificationDataAdapter);
    }

    @Bean
    public NotificationManager notificationManager(NotificationDataAdapter notificationDataAdapter) {
        return new NotificationManager(notificationDataAdapter);
    }

    @Bean
    public PotentialProjectManager potentialProjectManager(PotentialProjectDataAdapter potentialProjectDataAdapter,
                                                           EventManager eventManager,
                                                           NotificationDataAdapter notificationDataAdapter,
                                                           ProjectDataAdapter projectDataAdapter
    ) {
        return new PotentialProjectManager(potentialProjectDataAdapter, eventManager, notificationDataAdapter, projectDataAdapter);
    }

    @Bean
    public ProspectAnalyzer prospectAnalyzer(ProspectDataAdapter prospectDataAdapter, NotificationDataAdapter notificationDataAdapter,
                                             EventManager eventManager) {
        return new ProspectAnalyzer(prospectDataAdapter, notificationDataAdapter, eventManager);
    }

    @Bean
    public ProspectManager prospectManager(ProspectDataAdapter prospectDataAdapter) {
        return new ProspectManager(prospectDataAdapter);
    }

    @Bean
    public ProspectStatisticsGenerator prospectStatisticsGenerator(ProspectDataAdapter prospectDataAdapter) {
        return new ProspectStatisticsGenerator(prospectDataAdapter);
    }

    @Bean
    public AddressManager addressManager(AddressDataAdapter addressDataAdapter) {
        return new AddressManager(addressDataAdapter);
    }

    @Bean
    public EmailManager emailManager(ThymeleafConfigurer thymeleafTemplateEngine, GmailApi gmailApi, ProspectDataAdapter prospectDataAdapter) {
        if (nonNull(gmailApi)) return new EmailManager(thymeleafTemplateEngine, gmailApi, prospectDataAdapter);
        else return new EmailManager(thymeleafTemplateEngine, prospectDataAdapter);
    }

    @Bean
    public ProspectFilterManager prospectFilterManager(ProspectDataAdapter prospectDataAdapter,
                                                       ProspectFilterDataAdapter prospectFilterDataAdapter) {
        return new ProspectFilterManager(prospectDataAdapter, prospectFilterDataAdapter);
    }

    @Bean
    public DocumentManager documentManager(DocumentDataAdapter documentDataAdapter, ProspectDataAdapter prospectDataAdapter) {
        return new DocumentManager(new GoogleDriveApi(), documentDataAdapter, prospectDataAdapter);
    }
}