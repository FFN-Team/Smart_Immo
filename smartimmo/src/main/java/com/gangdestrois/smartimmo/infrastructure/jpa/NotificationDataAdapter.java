package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.event.model.Notify;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.port.NotificationSpi;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.NotificationEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.NotificationRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.PotentialProjectRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class NotificationDataAdapter implements NotificationSpi {
    private final NotificationRepository notificationRepository;
    private final PotentialProjectRepository potentialProjectRepository;
    private final ProspectRepository prospectRepository;

    @Autowired
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
    public Optional<Event<Prospect>> findProspectNotificationById(Long prospectNotificationId) {
        return notificationRepository.findById(prospectNotificationId)
                .map(NotificationEntity::toProspectNotificationModel);
    }

    @Override
    public Optional<Event<? extends Notify>> findNotificationById(Long id) {
        return notificationRepository.findById(id).map(NotificationEntity::toModel);
    }

    public List<Event<Notify>> findNotificationByElementIdAndStatusAndEventType(Long elementId,
                                                                                List<NotificationStatus> notificationStatuses,
                                                                                EventType eventType) {
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        switch (eventType) {
            case PROJECT_DUE_DATE_APPROACHING -> {
                var potentialProject = potentialProjectRepository.findById(elementId).orElse(null);
                notificationEntities = notificationStatuses.stream()
                        .map(status ->
                                notificationRepository.findNotificationEntitiesByPotentialProjectAndStatusAndType
                                        (potentialProject, status, eventType))
                        .flatMap(List::stream)
                        .toList();
            }
            case PROSPECT_MAY_BUY_BIGGER_HOUSE -> {
                var prospect = prospectRepository.findById(elementId).orElse(null);
                notificationEntities = notificationStatuses.stream().map(status -> notificationRepository
                                .findNotificationEntitiesByProspectAndNotificationStatusAndType(prospect, status, eventType))
                        .flatMap(List::stream)
                        .toList();
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
                event.getElement(),
                event.getEventType()
        );
        NotificationEntity savedNotification = notificationRepository.save(receivedNotification);
        return savedNotification.toModel();
    }

    //mal fait
    @Override
    public Long saveNotification(Event<? extends Notify> event) {
        if (event.getElement() instanceof PotentialProject)
            return savePotentialProjectNotification((Event<PotentialProject>) event);
        if (event.getElement() instanceof Prospect) return saveProspectNotification((Event<Prospect>) event);
        throw new RuntimeException("to do");
    }

    @Override
    public List<Event<Notify>> findNotificationByEventType(EventType eventType) {
        return notificationRepository.findNotificationEntitiesByType(eventType)
                .stream().map(NotificationEntity::toModel)
                .toList();
    }
}
