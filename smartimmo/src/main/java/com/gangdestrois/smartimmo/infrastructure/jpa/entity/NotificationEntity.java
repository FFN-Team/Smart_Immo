package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.enums.Status;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Long id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "message")
    private String message;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @OneToOne(targetEntity = PotentialProjectEntity.class)
    @JoinColumn(name = "fk_potential_project", referencedColumnName = "id_potential_project")
    private PotentialProjectEntity potentialProject;
    @OneToOne(targetEntity = ProspectEntity.class)
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    private ProspectEntity prospect;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventType type;

    public NotificationEntity() {
    }

    public NotificationEntity(Status status, String message, Priority priority,
                              PotentialProjectEntity potentialProjectEntity, EventType eventType) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.potentialProject = potentialProjectEntity;
        this.type = eventType;
    }

    public NotificationEntity(Status status, String message, Priority priority, ProspectEntity prospect, EventType eventType) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.prospect = prospect;
        this.type = eventType;
    }

    public NotificationEntity(Status status, String message, Priority priority) {
        this.status = status;
        this.message = message;
        this.priority = priority;
    }

    public NotificationEntity(Long id, Status status, String message, Priority priority,
                              Model element) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.priority = priority;
        if (element.getClass().equals(Prospect.class)) {
            Prospect prospectElement = (Prospect) element;
            this.prospect = new ProspectEntity(prospectElement.id());
        }
        if (element.getClass().equals(PotentialProject.class)) {
            PotentialProject potentialProjectElement = (PotentialProject) element;
            this.potentialProject = new PotentialProjectEntity(potentialProjectElement.id());
        }
    }

    public Event<PotentialProject> toProjectNotificationModel() {
        return new Event(this.id, status, message, priority, potentialProject.toModel(), type);
    }

    public Event toModel() {
        return new Event(this.id, this.status, this.message, this.priority, getElement(), type);
    }

    public Model getElement() {
        return nonNull(this.prospect) ? this.prospect.toModel() : this.potentialProject.toModel();
    }

    public Map<EventType, Set<Event>> toModel(Map<EventType, Set<Event>> map) {
        if (map.containsKey(type)) map.get(type).add(this.toModel());
        else {
            var events = new HashSet<Event>();
            events.add(this.toProjectNotificationModel());
            map.put(type, events);
        }
        return map;
    }

    public Long getId() {
        return id;
    }
}
