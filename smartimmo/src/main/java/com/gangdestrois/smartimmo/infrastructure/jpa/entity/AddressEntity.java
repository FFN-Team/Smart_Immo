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

    @Column(name = "street_number")
    private Integer streetNumber;

    @ManyToOne(targetEntity = StreetEntity.class)
    @JoinColumn(name = "fk_street", referencedColumnName = "id_street")
    private StreetEntity street;

    @ManyToOne(targetEntity = CityEntity.class)
    @JoinColumn(name = "fk_city", referencedColumnName = "id_city")
    private CityEntity city;

    @OneToOne(mappedBy = "address")
    private PropertyEntity property;

    public AddressEntity() {
    }

    public AddressEntity(Long id, Integer streetNumber, StreetEntity street, CityEntity city) {
        this.id = id;
        this.streetNumber = streetNumber;
        this.street = street;
        this.city = city;
    }

    public Address toModel() {
        return new Address(
            id,
            streetNumber,
            nonNull(street) ? street.toModel() : null,
            nonNull(city) ? city.toModel() : null
        );
    }

    public static AddressEntity toEntity(Address address){
        return new AddressEntity(
            address.id(),
            address.streetNumber(),
            StreetEntity.toEntity(address.street()),
            CityEntity.toEntity(address.city())
        );
    }
}
