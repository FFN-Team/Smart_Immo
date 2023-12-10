package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.ProspectNotification;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
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
    public Integer savePotentialProjectNotification(Event<PotentialProject> event) {
        var potentialProject = potentialProjectRepository.findById(event.getElement().getId()).orElse(null);
        var notificationToSave = new NotificationEntity(event.state(), event.message(), event.priority(), potentialProject);
        return save(notificationToSave);
    }

    @Override
    @Transactional
    public Integer saveProspectNotification(Event<Prospect> event) {
        var potentialProject = prospectRepository.findById(event.getElement().getId()).orElse(null);
        var notificationToSave = new NotificationEntity(event.state(), event.message(), event.priority(), potentialProject);
        return save(notificationToSave);
    }

    private Integer save(NotificationEntity notificationToSave) {
        var savedNotification = notificationRepository.save(notificationToSave);
        return savedNotification.getId();
    }

    @Override
    public Optional<Event<PotentialProject>> findProjectNotificationById(Integer projectNotificationId) {
        return notificationRepository.findById(projectNotificationId)
                .map(NotificationEntity::toProjectNotificationModel);
    }
}
