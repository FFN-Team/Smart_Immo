package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
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
    public List<Event<PotentialProject>> findAllProjectNotification() {
        return notificationRepository.findNotificationEntitiesByPotentialProjectNotNull()
                .stream()
                .map(NotificationEntity::toProjectNotificationModel)
                .toList();
    }

    @Override
    @Transactional
    public Integer save(Event<PotentialProject> event) {
        var potentialProject = potentialProjectRepository.findById(event.getElement().getId()).orElse(null);
        var notificationToSave = new NotificationEntity(event.state(), event.message(), event.priority(), potentialProject);
        var savedNotification = notificationRepository.save(notificationToSave);
        return savedNotification.getId();
    }

    @Override
    public Optional<Event<PotentialProject>> findProjectNotificationById(Integer projectNotificationId) {
        return notificationRepository.findById(projectNotificationId)
                .map(NotificationEntity::toProjectNotificationModel);
    }
}
