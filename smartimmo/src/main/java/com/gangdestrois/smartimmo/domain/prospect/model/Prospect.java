package com.gangdestrois.smartimmo.domain.prospect.model;

import com.gangdestrois.smartimmo.domain.event.Priority;
import com.gangdestrois.smartimmo.domain.event.ProspectNotification;
import com.gangdestrois.smartimmo.domain.event.State;

import java.util.Date;
import java.util.List;

public class Prospect {
    private final Integer id;
    private final String contactOrigine;
    private final String title;
    private final String lastName;
    private final String firstName;
    private final Date dateOfBirth;
    private final String profession;
    private final Long mobile;
    private final String mail;
    private final boolean authorizeContactOnSocialMedia;
    private final Home home;
    private final List<Owner> owners;

    public Prospect(Integer id, String contactOrigine, String title, String lastName, String firstName,
                    Date dateOfBirth, String profession, long mobile, String mail,
                    boolean authorizeContactOnSocialMedia, Home home, List<Owner> owners) {
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
        this.home = home;
        this.owners = owners;
    }

    public Integer getId() {
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

    public Home getHome() {
        return home;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public ProspectNotification mapToProspectNotification() {
        return new ProspectNotification(
                this,
                State.TO_READ,
                String.format("Suggestion : le prospect %s %s est susceptible de vouloir changer de logement. " +
                                "Vous pouvez consulter sa fiche en cliquant sur le bouton ci-dessous.",
                        this.firstName, this.lastName),
                Priority.LOW);
    }
}
