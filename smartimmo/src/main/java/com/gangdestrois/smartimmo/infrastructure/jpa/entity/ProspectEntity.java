package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "prospect")
public class ProspectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prospect")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_origin")
    private ContactOrigin contactOrigin;
    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "profession")
    private Profession profession;
    @Column(name = "mobile")
    private Long mobile;
    @Column(name = "mail")
    private String mail;
    @Column(name = "authorize_contact_on_social_media")
    private Boolean authorizeContactOnSocialMedia;
    @JoinColumn(name = "fk_home", referencedColumnName = "id_home")
    @ManyToOne(targetEntity = HomeEntity.class)
    private HomeEntity home;
    @OneToMany(mappedBy = "prospect", fetch = FetchType.EAGER)
    private Set<OwnerEntity> owners;
    @OneToMany(mappedBy = "prospect")
    private Set<ProjectEntity> projects;
    @OneToMany(mappedBy = "prospect")
    private List<FileEntity> files;

    //private Adresse adresseTravail;
    //private Prospect personneCompagnon;

    public ProspectEntity() {
    }

    public ProspectEntity(Long id) {
        this.id = id;
    }

    public ProspectEntity(Long id, ContactOrigin contactOrigin, Title title, String lastName,
                          String firstName, LocalDate dateOfBirth, Profession profession, Long mobile,
                          String mail, Boolean authorizeContactOnSocialMedia, HomeEntity home,
                          Set<OwnerEntity> owners) {
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

    public ProspectEntity(Long id, ContactOrigin contactOrigin, Title title, String lastName, String firstName,
                          LocalDate dateOfBirth, Profession profession, Long mobile, String mail,
                          Boolean authorizeContactOnSocialMedia, HomeEntity home) {
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
    }

    public Prospect toModel() {
        return new Prospect.Builder()
                .id(id)
                .contactOrigin(contactOrigin)
                .title(title)
                .home(nonNull(home) ? home.toModel() : null)
                .lastName(lastName)
                .firstName(firstName)
                .dateOfBirth(dateOfBirth)
                .authorizeContactOnSocialMedia(nonNull(authorizeContactOnSocialMedia) ? authorizeContactOnSocialMedia : null)
                .mail(mail)
                .profession(profession)
                .mobile(mobile)
                .owners(owners.stream().map(OwnerEntity::toModel).toList())
                .build();
    }

    public static ProspectEntity fromModelToEntity(Prospect prospect) {
        return new ProspectEntity(
                prospect.id(), prospect.getContactOrigin(), prospect.getTitle(), prospect.getLastName(), prospect.getFirstName(),
                prospect.getDateOfBirth(), prospect.getProfession(), prospect.getMobile(), prospect.getMail(),
                prospect.authorizeContactOnSocialMedia(), HomeEntity.fromModelToEntity(prospect.getHome()/*, rq : ici, il manque set owner*/)
        );
    }

    public String getCompleteName() {
        return firstName + lastName;
    }
}