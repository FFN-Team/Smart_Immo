package com.gangdestrois.smartimmo.domain.prospect.entite;

import java.util.Date;

public class Prospect {
    private Long id;
    private String origineContact;
    private String civilite;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String profession;
    private Long telMobil;
    private String mail;
    private boolean authoriseContactReseaux;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;
    //private Foyer foyer;


    public Prospect(Long id, String origineContact, String civilite, String nom, String prenom,
                    Date dateNaissance, String profession, long telMobil, String mail,
                    boolean authoriseContactReseaux) {
        this.id = id;
        this.origineContact = origineContact;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.profession = profession;
        this.telMobil = telMobil;
        this.mail = mail;
        this.authoriseContactReseaux = authoriseContactReseaux;
    }

    public Long getId() {
        return id;
    }

    public String getOrigineContact() {
        return origineContact;
    }

    public String getCivilite() {
        return civilite;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public Long getTelMobil() {
        return telMobil;
    }

    public String getMail() {
        return mail;
    }

    public boolean isAuthoriseContactReseaux() {
        return authoriseContactReseaux;
    }
}
