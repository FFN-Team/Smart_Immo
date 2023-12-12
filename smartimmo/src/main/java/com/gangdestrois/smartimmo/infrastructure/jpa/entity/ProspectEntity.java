package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "prospect")
public class ProspectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prospect")
    private Integer id;
    @Column(name = "contact_origin") /////////// mettre un enum ici ? /////////////
    private String contactOrigine;
    @Column(name = "title")        /////////// mettre un enum ici ? /////////////
    private String title;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "profession")
    private String profession;
    @Column(name = "mobile")
    private long mobile;
    @Column(name = "mail")
    private String mail;
    @Column(name = "authorize_contact_on_social_media")
    private boolean authorizeContactOnSocialMedia;
    @JoinColumn(name = "fk_home", referencedColumnName = "id_home")
    @ManyToOne(targetEntity = HomeEntity.class)
    private HomeEntity home;
    @OneToMany(mappedBy = "prospect")
    private Set<OwnerEntity> owners;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;

    public Prospect toModel() {
        return new Prospect(
                id, contactOrigine, title, lastName, firstName, dateOfBirth,
                profession, mobile, mail, authorizeContactOnSocialMedia,
                nonNull(home) ? home.toModel() : null,
                this.owners.stream().map(OwnerEntity::toModel).toList()
        );
    }
}