package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer id;

    public Integer id() {
        return id;
    }
}
