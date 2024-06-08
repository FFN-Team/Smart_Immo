package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import jakarta.persistence.*;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Column(name = "flat_number")
    private Integer flat_number;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "street_number")
    private Integer streetNumber;

    @ManyToOne(targetEntity = StreetEntity.class)
    @JoinColumn(name = "fk_street", referencedColumnName = "id_street")
    private StreetEntity street;

    @ManyToOne(targetEntity = AreaEntity.class)
    @JoinColumn(name = "fk_area", referencedColumnName = "id_area")
    private AreaEntity area;

    @ManyToOne(targetEntity = CityEntity.class)
    @JoinColumn(name = "fk_city", referencedColumnName = "id_city")
    private CityEntity city;

    @OneToOne(mappedBy = "address")
    private PropertyEntity property;

    public AddressEntity() {
    }

    public AddressEntity(Long id, Integer flat_number, Integer floor, Integer streetNumber, StreetEntity street, AreaEntity area, CityEntity city) {
        this.id = id;
        this.flat_number = flat_number;
        this.floor = floor;
        this.streetNumber = streetNumber;
        this.street = street;
        this.area = area;
        this.city = city;
    }

    public Address toModel() {
        return new Address(
                id,
                flat_number,
                floor,
                streetNumber,
                nonNull(street) ? street.toModel() : null,
                nonNull(area) ? area.toModel() : null,
                nonNull(city) ? city.toModel() : null
        );
    }

    public static AddressEntity toEntity(Address address) {
        return new AddressEntity(
                address.id(),
                address.flat_number(),
                address.floor(),
                address.streetNumber(),
                StreetEntity.toEntity(address.street()),
                AreaEntity.fromModelToEntity(address.area()),
                CityEntity.toEntity(address.city())
        );
    }
}
