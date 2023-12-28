package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Child;
import com.gangdestrois.smartimmo.domain.prospect.model.Home;
import com.gangdestrois.smartimmo.domain.prospect.model.MaritalStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "home")
public class HomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_home")
    private Integer id;
    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @OneToMany(mappedBy = "home")
    private Set<ProspectEntity> prospects;
    @OneToMany(mappedBy = "home")
    private Set<ChildEntity> children;

    public HomeEntity(MaritalStatus maritalStatus, Set<ChildEntity> children) {
        this.maritalStatus = maritalStatus;
        this.children = children;
    }

    public HomeEntity() {}

    public static HomeEntity fromModelToEntity(Home home) {
        return (home != null) ?
                new HomeEntity(home.maritalStatus(),
                        home.children().stream().map(ChildEntity::fromModelToEntity).collect(Collectors.toSet()))
                : null;
    }

    public Home toModel() {
        return new Home(this.maritalStatus,
                this.children.stream().map(ChildEntity::toModel).toList());
    }
}
