package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.Status;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.persistence.*;

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

    public NotificationEntity() {
    }

    public NotificationEntity(Status status, String message, Priority priority, PotentialProjectEntity potentialProjectEntity) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.potentialProject = potentialProjectEntity;
    }

    public NotificationEntity(Status status, String message, Priority priority, ProspectEntity prospect) {
        this.status = status;
        this.message = message;
        this.priority = priority;
        this.prospect = prospect;
    }

    public NotificationEntity(Status status, String message, Priority priority) {
        this.status = status;
        this.message = message;
        this.priority = priority;
    }

    public NotificationEntity(Event event) {
        new NotificationEntity(event.status(), event.message(), event.priority());
    }

    public NotificationEntity(Long id, Status status, String message, Priority priority,
                              Model element) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.priority = priority;
        if (element.getClass().equals(Prospect.class))
        {
            Prospect prospectElement = (Prospect)element;
            this.prospect = new ProspectEntity(prospectElement.getId());
        }
        if (element.getClass().equals(PotentialProject.class))
        {
            PotentialProject potentialProjectElement = (PotentialProject)element;
            this.potentialProject = new PotentialProjectEntity(potentialProjectElement.getId());
        }
    }

    public Event<PotentialProject> toProjectNotificationModel() {
        return new Event(status, message, priority, potentialProject.toModel());
    }

    public Event toModel(){
        return new Event(this.id, this.status, this.message, this.priority, getElement());
    }

    public Model getElement(){
        return nonNull(this.prospect) ? this.prospect.toModel() : this.potentialProject.toModel();
    }

    public Long getId() {
        return id;
    }
}
