package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.bien.BienService;
import com.gangdestrois.smartimmo.domain.notification.EventManager;
import com.gangdestrois.smartimmo.domain.notification.NotificationAlertListener;
import com.gangdestrois.smartimmo.domain.project.PotentialProjectManager;
import com.gangdestrois.smartimmo.infrastructure.jpa.BienDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.EventTypeNotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.NotificationDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.PotentialProjectDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.EventTypeNotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    public PotentialProjectDataAdapter potentialProjectDataAdapter(PotentialProjectRepository potentialProjectRepository) {
        return new PotentialProjectDataAdapter(potentialProjectRepository);
    }

    @Bean
    public EventManager eventManager() {
        return new EventManager();
    }

    @Bean
    public PotentialProjectManager projectManager(PotentialProjectDataAdapter potentialProjectDataAdapter,
                                                  EventManager eventManager) {
        return new PotentialProjectManager(potentialProjectDataAdapter, eventManager);
    }

    @Bean
    public NotificationDataAdapter notificationDataAdapter(NotificationRepository notificationRepository) {
        return new NotificationDataAdapter(notificationRepository);
    }

    @Bean
    public EventTypeNotificationDataAdapter eventTypeNotificationDataAdapter(EventTypeNotificationRepository eventTypeNotificationRepository) {
        return new EventTypeNotificationDataAdapter(eventTypeNotificationRepository);
    }

    @Bean
    public NotificationAlertListener notificationAlertListener(EventTypeNotificationDataAdapter eventTypeNotificationDataAdapter) {
        return new NotificationAlertListener(eventTypeNotificationDataAdapter);
    }

}