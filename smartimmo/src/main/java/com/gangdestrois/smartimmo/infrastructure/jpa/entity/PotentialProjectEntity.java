package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "potential_project")
public class PotentialProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "potential_project_id")
    private Integer id;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @JoinColumn(name = "project_id")
    @ManyToOne(targetEntity = ProjectEntity.class)
    private ProjectEntity project;

    @Column(name = "priority")
    private String priority;

    @Column(name = "notification_date")
    private LocalDate notificationDate;

    public String getMessage() {
        return String.format("Rappel : la date prévue pour le projet %d approche. Vous pouvez consulter " +
                "le projet ci-dessous pour reprendre connaissance avec le projet.", project.id());
    }

    public PotentialProject toModel() {
        return new PotentialProject(id, dueDate, getMessage(), Priority.valueOf(priority));
    }
}
