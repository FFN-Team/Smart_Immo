package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.acquereur.AcquereurManager;
import com.gangdestrois.smartimmo.domain.acquereur.PropertiesFinder;
import com.gangdestrois.smartimmo.domain.bien.BienService;
import com.gangdestrois.smartimmo.infrastructure.jpa.AcquereurDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.BienDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.CriteresBienAcquereurRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.AcquereurRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;
import com.gangdestrois.smartimmo.domain.bien.BienService;
import com.gangdestrois.smartimmo.domain.event.EventManager;
import com.gangdestrois.smartimmo.domain.event.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.potentialProject.PotentialProjectManager;
import com.gangdestrois.smartimmo.infrastructure.jpa.*;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gangdestrois.smartimmo.infrastructure.jpa.repository")
public class BeanConfiguration {
    @Bean
    public BienDataAdapter bienDataAdapter(BienRepository bienRepository) {
        return new BienDataAdapter(bienRepository);
    }

    @Bean
    public BienService bienService(BienDataAdapter bienDataAdapter) {
        return new BienService(bienDataAdapter);
    }

    @Bean
    public AcquereurDataAdapter acquereurDataAdapter(CriteresBienAcquereurRepository acquereurCriteresBienRepository)
    {return new AcquereurDataAdapter(acquereurCriteresBienRepository);}

    @Bean
    public AcquereurManager acquereurManager(AcquereurDataAdapter acquereurDataAdapter){return
            new AcquereurManager(acquereurDataAdapter);}

    @Bean
    public PropertiesFinder propertiesFinder(AcquereurDataAdapter acquereurDataAdapter, BienDataAdapter bienDataAdapter){
        return new PropertiesFinder(acquereurDataAdapter,bienDataAdapter);
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
                                                           PotentialProjectRepository potentialProjectRepository) {
        return new NotificationDataAdapter(notificationRepository, potentialProjectRepository);
    }

    @Bean
    public PotentialProjectManager potentialProjectManager(PotentialProjectDataAdapter potentialProjectDataAdapter,
                                                           EventManager eventManager,
                                                           NotificationDataAdapter notificationDataAdapter) {
        return new PotentialProjectManager(potentialProjectDataAdapter, eventManager, notificationDataAdapter);
    }
}