package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.Event;
import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.ProjectNotification;
import com.gangdestrois.smartimmo.domain.event.State;
import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    private Integer id;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
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

    public NotificationEntity(State state, String message, Priority priority, PotentialProjectEntity potentialProjectEntity) {
        this.state = state;
        this.message = message;
        this.priority = priority;
        this.potentialProject = potentialProjectEntity;
    }

    public NotificationEntity(State state, String message, Priority priority, ProspectEntity prospect) {
        this.state = state;
        this.message = message;
        this.priority = priority;
        this.prospect = prospect;
    }

    public NotificationEntity(State state, String message, Priority priority) {
        this.state = state;
        this.message = message;
        this.priority = priority;
    }

    public ProjectNotification toModel() {
        return new ProjectNotification(potentialProject.toModel(), state, message, priority);
    }

    public NotificationEntity(Event event) {
        new NotificationEntity(event.state(), event.message(), event.priority());
    }

    public Integer getId() {
        return id;
    }
}
