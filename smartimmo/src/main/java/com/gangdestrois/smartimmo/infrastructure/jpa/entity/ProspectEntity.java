package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "prospect")
public class ProspectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prospect")
    private Long id;

    @Column(name = "origine_contact") /////////// mettre un enum ici ? /////////////
    private String contactOrigine;

    @Column(name = "civilite")        /////////// mettre un enum ici ? /////////////
    private String title;

    @Column(name = "nom")
    private String lastName;

    @Column(name = "prenom")
    private String firstName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance")
    private Date dateOfBirth;

    @Column(name = "profession")
    private String profession;

    @Column(name = "tel_mobile")
    private long mobile;

    @Column(name = "mail")
    private String mail;

    @Column(name="authorise_contact_reseaux")
    private boolean authorizeContactOnSocialMedia;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;
    //private Foyer foyer;

    public Prospect toModel(){ return new Prospect(id, contactOrigine, title, lastName, firstName, dateOfBirth,
            profession, mobile,mail, authorizeContactOnSocialMedia);
    }
}