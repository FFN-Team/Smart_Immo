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
    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @OneToMany(mappedBy = "home")
    private Set<ProspectEntity> prospects;
    @OneToMany(mappedBy = "home")
    private Set<ChildEntity> children;

    public Home toModel() {
        return new Home(this.maritalStatus,
                this.children.stream().map(ChildEntity::toModel).toList());
    }
}
