package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.notification.Event;
import com.gangdestrois.smartimmo.domain.notification.Priority;
import com.gangdestrois.smartimmo.domain.notification.ProjectNotification;
import com.gangdestrois.smartimmo.domain.notification.State;
import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "message")
    private String message;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "ref_project")
    @OneToOne(targetEntity = PotentialProjectEntity.class)
    private PotentialProjectEntity project;

    public NotificationEntity(){
    }

    public NotificationEntity(State state, String message, Priority priority) {
        this.state = state;
        this.message = message;
        this.priority = priority;
    }

    public ProjectNotification toModel() {
        return new ProjectNotification(project.toModel(), state, message, priority);
    }

    public NotificationEntity(Event event) {
        new NotificationEntity(event.state(), event.message(), event.priority());
    }
}
