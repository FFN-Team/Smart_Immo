package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.ProspectNotification;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class NotificationDataAdapter implements NotificationSpi {
    private final NotificationRepository notificationRepository;
    private final PotentialProjectRepository potentialProjectRepository;
    private final ProspectRepository prospectRepository;

    public NotificationDataAdapter(NotificationRepository notificationRepository,
                                   PotentialProjectRepository potentialProjectRepository,
                                   ProspectRepository prospectRepository) {
        this.notificationRepository = notificationRepository;
        this.potentialProjectRepository = potentialProjectRepository;
        this.prospectRepository = prospectRepository;
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
    @Transactional
    public Integer save(ProspectNotification prospectNotification) {
        var potentialProject = prospectRepository.findById(prospectNotification.prospect().getId()).orElse(null);
        var notificationToSave = new NotificationEntity(prospectNotification.state(),
                prospectNotification.message(),
                prospectNotification.priority(),
                potentialProject);
        var savedNotification = notificationRepository.save(notificationToSave);
        return savedNotification.getId();
    }

    @Override
    public Optional<Event<PotentialProject>> findProjectNotificationById(Integer projectNotificationId) {
        return notificationRepository.findById(projectNotificationId)
                .map(NotificationEntity::toProjectNotificationModel);
    }
}
