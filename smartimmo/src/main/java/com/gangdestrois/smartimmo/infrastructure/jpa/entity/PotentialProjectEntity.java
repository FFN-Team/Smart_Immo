package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.potentialProject.model.PotentialProject;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "potential_project")
public class PotentialProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_potential_project")
    private Long id;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @OneToOne(targetEntity = ProjectEntity.class)
    @JoinColumn(name = "fk_project", referencedColumnName = "id_project")
    private ProjectEntity project;
    @Column(name = "priority")
    private String priority;
    @Column(name = "notification_date")
    private LocalDate notificationDate;

    public PotentialProjectEntity() {
    }

    public PotentialProjectEntity(Long id) {
        this.id = id;
    }

    public String getMessage() {
        var prospect = project.getProspect().orElseThrow(() -> new NotFoundException(project.id(), "no prospect found"));
        return String.format("Rappel : la date prévue pour le projet de %s approche. Vous pouvez consulter " +
                "le projet ci-dessous pour reprendre connaissance avec le projet.", prospect.getCompleteName());
    }

    public PotentialProject toModel() {
        return new PotentialProject(id, dueDate, getMessage(), Priority.valueOf(priority), project.getProspect()
                .map(ProspectEntity::toModel).orElse(null));
    }
}
