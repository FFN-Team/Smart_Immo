package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Integer id;
    @OneToOne(mappedBy = "project")
    private PotentialProjectEntity potentialProject;

    public Integer id() {
        return id;
    }
}
