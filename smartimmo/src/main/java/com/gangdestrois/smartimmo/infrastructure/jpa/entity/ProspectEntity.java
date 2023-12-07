package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.entite.Prospect;
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
    private String origineContact;

    @Column(name = "civilite")        /////////// mettre un enum ici ? /////////////
    private String civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "profession")
    private String profession;

    @Column(name = "tel_mobile")
    private long telMobile;

    @Column(name = "mail")
    private String mail;

    @Column(name="authorise_contact_reseaux")
    private boolean authoriseContactReseaux;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;
    //private Foyer foyer;

    public Prospect toModel(){ return new Prospect(id,origineContact,civilite,nom,prenom,dateNaissance,
            profession,telMobile,mail,authoriseContactReseaux);
    }
}