package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.project.ProjetAnticipe;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "projet")
public class ProjetAnticipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bien_id")
    private Long id;

    @Column(name = "date_prevue")
    private LocalDate datePrevue;

    public ProjetAnticipe toModel() {
        return new ProjetAnticipe(id, datePrevue);
    }
}
