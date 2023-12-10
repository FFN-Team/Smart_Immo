package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Home;
import com.gangdestrois.smartimmo.domain.prospect.model.MaritalStatus;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "home")
public class HomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_home")
    private Integer id;
    @Column(name = "maritalStatus")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @OneToMany(mappedBy = "home")
    private Set<ProspectEntity> prospectEntities;
    @OneToMany(mappedBy = "home")
    private Set<ChildrenEntity> children;

    public Home toModel() {
        return new Home(this.maritalStatus,
                this.children.stream().map(ChildrenEntity::toModel).toList());
    }
}
