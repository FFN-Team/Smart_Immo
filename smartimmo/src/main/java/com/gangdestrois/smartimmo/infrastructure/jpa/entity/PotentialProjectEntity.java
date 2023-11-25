package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.notification.Priority;
import com.gangdestrois.smartimmo.domain.project.PotentialProject;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "projet")
public class PotentialProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bien_id")
    private Long id;

    @Column(name = "date_prevue")
    private LocalDate datePrevue;

    @Column(name="refProjet")
    private Long projet;

    @Column(name="priorite")
    private String priorite;

    public String getMessage(){
        return String.format("Rappel : la date prévue pour le projet %d approche. Vous pouvez consulter " +
                "le projet ci-dessous pour reprendre connaissance avec le projet.", projet);
    }

    public PotentialProject toModel() {
        return new PotentialProject(id, datePrevue, getMessage(), Priority.valueOf(priorite));
    }
}
