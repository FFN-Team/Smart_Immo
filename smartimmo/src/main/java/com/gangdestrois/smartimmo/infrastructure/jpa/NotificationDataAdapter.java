package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.EventType;
import com.gangdestrois.smartimmo.domain.event.Status;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
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
    @Transactional
    public Long savePotentialProjectNotification(Event<PotentialProject> event) {
        var potentialProject = potentialProjectRepository.findById(event.getElement().id()).orElse(null);
        var notificationToSave = new NotificationEntity(event.status(), event.message(), event.priority(),
                potentialProject, event.getEventType());
        return save(notificationToSave);
    }

    @Override
    @Transactional
    public Long saveProspectNotification(Event<Prospect> event) {
        var potentialProject = prospectRepository.findById(event.getElement().id()).orElse(null);
        var notificationToSave = new NotificationEntity(event.status(), event.message(), event.priority(),
                potentialProject, event.getEventType());
        return save(notificationToSave);
    }

    private Long save(NotificationEntity notificationToSave) {
        var savedNotification = notificationRepository.save(notificationToSave);
        return savedNotification.getId();
    }

    @Override
    public Optional<Event<PotentialProject>> findProjectNotificationById(Long projectNotificationId) {
        return notificationRepository.findById(projectNotificationId)
                .map(NotificationEntity::toProjectNotificationModel);
    }

    @Override
    public Optional<Event> findNotificationById(Long id) {
        return notificationRepository.findById(id).map(NotificationEntity::toModel);
    }

    public List<Event> findNotificationByElementIdAndStatusAndEventType(Long elementId, Status status, EventType eventType) {
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        switch (eventType) {
            case PROJECT_DUE_DATE_APPROACHING -> {
                var potentialProject = potentialProjectRepository.findById(elementId).orElse(null);
                notificationEntities = notificationRepository
                        .findNotificationEntitiesByPotentialProjectAndStatusAndType(potentialProject, status, eventType);
            }
            case PROSPECT_MAY_BUY_BIGGER_HOUSE -> {
                var prospect = prospectRepository.findById(elementId).orElse(null);
                notificationEntities = notificationRepository
                        .findNotificationEntitiesByProspectAndStatusAndType(prospect, status, eventType);
            }
        }
        return notificationEntities
                .stream()
                .map(NotificationEntity::toModel)
                .toList();
    }

    @Override
    @Transactional
    public Event save(Event event) {
        NotificationEntity receivedNotification = new NotificationEntity(
                event.getId(),
                event.status(),
                event.message(),
                event.priority(),
                event.getElement()
        );
        NotificationEntity savedNotification = notificationRepository.save(receivedNotification);
        return savedNotification.toModel();
    }

    @Override
    public List<Event> findNotificationByEventType(EventType eventType) {
        return notificationRepository.findNotificationEntitiesByType(eventType)
                .stream().map(NotificationEntity::toModel)
                .toList();
    }
}
