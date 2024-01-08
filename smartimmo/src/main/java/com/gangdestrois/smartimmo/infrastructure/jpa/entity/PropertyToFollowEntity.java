package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.PropertyToFollowStatus;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.model.PropertyToFollow;
import jakarta.persistence.*;

@Entity
@Table(name = "property_to_follow")
public class PropertyToFollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_to_follow")
    private Long id;

    @OneToOne(targetEntity = BuyerEntity.class)
    @JoinColumn(name = "fk_buyer", referencedColumnName = "id_buyer")
    private BuyerEntity buyer;

    @OneToOne(targetEntity = PropertyEntity.class)
    @JoinColumn(name = "fk_property", referencedColumnName = "id_property")
    private PropertyEntity property;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PropertyToFollowStatus status;

    public PropertyToFollowEntity(BuyerEntity buyer, PropertyEntity property, PropertyToFollowStatus status) {
        this.buyer = buyer;
        this.property = property;
        this.status = status;
    }

    public PropertyToFollowEntity() { }

    public PropertyToFollow toModel() {
        return new PropertyToFollow(
                id, buyer.toModel(), property.toModel(), status
        );
    }

    public Long getId() {
        return id;
    }

}
