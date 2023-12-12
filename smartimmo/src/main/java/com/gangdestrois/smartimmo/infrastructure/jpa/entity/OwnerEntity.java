package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Owner;
import jakarta.persistence.*;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "owner")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owner")
    private Integer id;
    @Column(name = "active")
    private boolean active;
    @OneToOne(mappedBy = "owner")
    private PropertyOwnerEntity propertyOwner;
    @JoinColumn(name = "fk_prospect", referencedColumnName = "id_prospect")
    @ManyToOne(targetEntity = ProspectEntity.class)
    private ProspectEntity prospect;

    public Owner toModel() {
        return new Owner(
                nonNull(this.propertyOwner) ? this.propertyOwner.getAcquisitionDate() : null,
                nonNull(this.propertyOwner) && nonNull(this.propertyOwner.getProperty()) ?
                        this.propertyOwner.getProperty().toModel() : null,
                nonNull(this.propertyOwner) && this.propertyOwner.getMain());
    }
}
