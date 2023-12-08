package com.gangdestrois.smartimmo.domain.prospect.model;

import java.util.Date;

public class Prospect {
    private Long id;
    private String contactOrigine;
    private String title;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private String profession;
    private Long mobile;
    private String mail;
    private boolean authorizeContactOnSocialMedia;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;
    //private Foyer foyer;


    public Prospect(Long id, String contactOrigine, String title, String lastName, String firstName,
                    Date dateOfBirth, String profession, long mobile, String mail,
                    boolean authorizeContactOnSocialMedia) {
        this.id = id;
        this.contactOrigine = contactOrigine;
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
        this.mobile = mobile;
        this.mail = mail;
        this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
    }

    public Long getId() {
        return id;
    }

    public String getContactOrigine() {
        return contactOrigine;
    }

    public String getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfession() {
        return profession;
    }

    public Long getMobile() {
        return mobile;
    }

    public String getMail() {
        return mail;
    }

    public boolean authorizeContactOnSocialMedia() {
        return authorizeContactOnSocialMedia;
    }
}
