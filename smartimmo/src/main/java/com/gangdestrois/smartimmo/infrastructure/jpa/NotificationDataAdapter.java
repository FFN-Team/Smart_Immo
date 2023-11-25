package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;
import com.gangdestrois.smartimmo.domain.notification.port.NotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;

import java.util.List;

public class NotificationDataAdapter implements NotificationSpi {
    private final NotificationRepository notificationRepository;

    public NotificationDataAdapter(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<ProjectNotification> findAll() {
        return notificationRepository.findAll()
                .stream()
                .map(NotificationEntity::toModel)
                .toList();
    }
}
