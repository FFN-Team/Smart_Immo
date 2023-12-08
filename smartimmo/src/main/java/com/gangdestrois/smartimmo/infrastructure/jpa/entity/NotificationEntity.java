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
    @Column(name = "notification_id")
    private Integer id;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "message")
    private String message;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @JoinColumn(name = "potential_project_id")
    @OneToOne(targetEntity = PotentialProjectEntity.class)
    private PotentialProjectEntity potentialProject;

    public NotificationEntity() {
    }

    public NotificationEntity(State state, String message, Priority priority, PotentialProjectEntity potentialProjectEntity) {
        this.state = state;
        this.message = message;
        this.priority = priority;
        this.potentialProject = potentialProjectEntity;
    }

    public NotificationEntity(State state, String message, Priority priority) {
        this.state = state;
        this.message = message;
        this.priority = priority;
    }

    public ProjectNotification toProjectNotificationModel() {
        return new ProjectNotification(potentialProject.toModel(), state, message, priority);
    }

    public NotificationEntity(Event event) {
        new NotificationEntity(event.state(), event.message(), event.priority());
    }

    public Integer getId() {
        return id;
    }
}
