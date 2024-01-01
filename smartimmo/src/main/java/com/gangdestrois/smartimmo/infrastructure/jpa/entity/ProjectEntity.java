package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.project.Project;
import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;
    @ManyToOne(targetEntity = ProspectEntity.class)
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    private ProspectEntity prospect;
    @OneToOne(mappedBy = "project")
    private PotentialProjectEntity potentialProject;

    public Long id() {
        return id;
    }

    public Optional<ProspectEntity> getProspect() {
        return Optional.ofNullable(prospect);
    }

    public Project toModel() {
        return new Project(id, prospect.toModel());
    }
}
