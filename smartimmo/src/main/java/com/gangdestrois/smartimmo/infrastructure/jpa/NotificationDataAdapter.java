package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.ProjectNotification;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class NotificationDataAdapter implements NotificationSpi {
    private final NotificationRepository notificationRepository;
    private final PotentialProjectRepository potentialProjectRepository;

    public NotificationDataAdapter(NotificationRepository notificationRepository,
                                   PotentialProjectRepository potentialProjectRepository) {
        this.notificationRepository = notificationRepository;
        this.potentialProjectRepository = potentialProjectRepository;
    }

    @Override
    public List<ProjectNotification> findAll() {
        return notificationRepository.findAll()
                .stream()
                .map(NotificationEntity::toProjectNotificationModel)
                .toList();
    }

    @Override
    @Transactional
    public Integer save(ProjectNotification event) {
        var potentialProject = potentialProjectRepository.findById(event.potentialProject().id()).orElse(null);
        var notificationToSave = new NotificationEntity(event.state(), event.message(), event.priority(), potentialProject);
        var savedNotification = notificationRepository.save(notificationToSave);
        return savedNotification.getId();
    }

    @Override
    public Optional<ProjectNotification> findProjectNotificationById(Integer projectNotificationId) {
        return notificationRepository.findById(projectNotificationId)
                .map(NotificationEntity::toProjectNotificationModel);
    }
}
