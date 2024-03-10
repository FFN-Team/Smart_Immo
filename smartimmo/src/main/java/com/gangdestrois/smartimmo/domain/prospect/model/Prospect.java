package com.gangdestrois.smartimmo.domain.prospect.model;

import com.gangdestrois.smartimmo.domain.event.enums.EventType;
import com.gangdestrois.smartimmo.domain.event.enums.NotificationStatus;
import com.gangdestrois.smartimmo.domain.event.enums.Priority;
import com.gangdestrois.smartimmo.domain.event.model.Event;
import com.gangdestrois.smartimmo.domain.event.model.Notify;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;

import java.time.LocalDate;
import java.util.List;

public class Prospect implements Notify {
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

    private Prospect(Builder builder) {
        this.id = builder.id;
        this.contactOrigin = builder.contactOrigin;
        this.title = builder.title;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.dateOfBirth = builder.dateOfBirth;
        this.profession = builder.profession;
        this.mobile = builder.mobile;
        this.mail = builder.mail;
        this.authorizeContactOnSocialMedia = builder.authorizeContactOnSocialMedia;
        this.home = builder.home;
        this.owners = builder.owners;
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

    public static class Builder {
        Long id;
        ContactOrigin contactOrigin;
        Title title;
        String lastName;
        String firstName;
        LocalDate dateOfBirth;
        Profession profession;
        Long mobile;
        String mail;
        Boolean authorizeContactOnSocialMedia;
        Home home;
        List<Owner> owners;


        public Builder() {
            // Initialisez les valeurs par défaut si nécessaire
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public Builder contactOrigin(ContactOrigin contactOrigin) {
            this.contactOrigin = contactOrigin;
            return this;
        }

        public Builder title(Title title) {
            this.title = title;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder profession(Profession profession) {
            this.profession = profession;
            return this;
        }

        public Builder mobile(Long mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder authorizeContactOnSocialMedia(Boolean authorizeContactOnSocialMedia) {
            this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
            return this;
        }

        public Builder home(Home home) {
            this.home = home;
            return this;
        }

        public Builder owners(List<Owner> owners) {
            this.owners = owners;
            return this;
        }

        public Prospect build() {
            return new Prospect(this);
        }
    }
}
