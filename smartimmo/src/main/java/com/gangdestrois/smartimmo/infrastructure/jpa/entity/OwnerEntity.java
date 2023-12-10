package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Owner;
import jakarta.persistence.*;

@Entity
@Table(name = "owner")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Integer id;
    @Column(name = "active")
    private boolean active;
    @OneToOne(targetEntity = PropertyOwnerEntity.class)
    private PropertyOwnerEntity propertyOwnerEntity;
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    @ManyToOne(targetEntity = ProspectEntity.class)
    private ProspectEntity prospectEntity;

    public Owner toModel() {
        return new Owner(this.propertyOwnerEntity.getAcquisitionDate(),
                this.propertyOwnerEntity.getPropertyEntity().toModel(),
                this.propertyOwnerEntity.getMain());
    }
}
