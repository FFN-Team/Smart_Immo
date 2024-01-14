package com.gangdestrois.smartimmo.domain.prospect.model;

import com.gangdestrois.smartimmo.domain.Model;
import com.gangdestrois.smartimmo.domain.event.*;
import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.prospect.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.Profession;
import com.gangdestrois.smartimmo.domain.prospect.Title;

import java.time.LocalDate;
import java.util.List;

public class Prospect implements Model, Notify<Prospect> {
    private Long id;
    private final ContactOrigin contactOrigin;
    private final Title title;
    private final String lastName;
    private final String firstName;
    private final LocalDate dateOfBirth;
    private final Profession profession;
    private final Long mobile;
    private final String mail;
    private final Boolean authorizeContactOnSocialMedia;
    private final Home home;
    private final List<Owner> owners;

    public Prospect(Long id, ContactOrigin contactOrigin, Title title, String lastName, String firstName,
                    LocalDate dateOfBirth, Profession profession, Long mobile, String mail,
                    Boolean authorizeContactOnSocialMedia, Home home, List<Owner> owners) {
        this.id = id;
        this.contactOrigin = contactOrigin;
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

    @Override
    public Long id() {
        return id;
    }

    public ContactOrigin getContactOrigin() {
        return contactOrigin;
    }

    public Title getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getMobile() {
        return mobile;
    }

    public String getMail() {
        return mail;
    }

    public Boolean authorizeContactOnSocialMedia() {
        return authorizeContactOnSocialMedia;
    }

    public Home getHome() {
        return home;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public String getCompleteName() {
        return firstName + " " + lastName;
    }

    public Event<Prospect> mapToEvent() {
        return new Event(
                NotificationStatus.TO_READ,
                String.format("Suggestion : le prospect %s %s est susceptible de vouloir changer de logement. " +
                                "Vous pouvez consulter sa fiche en cliquant sur le bouton ci-dessous.",
                        this.firstName, this.lastName),
                Priority.LOW,
                this, EventType.PROSPECT_MAY_BUY_BIGGER_HOUSE);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
