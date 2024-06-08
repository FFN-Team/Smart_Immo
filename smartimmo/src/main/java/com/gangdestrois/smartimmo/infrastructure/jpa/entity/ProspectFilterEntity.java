package com.gangdestrois.smartimmo.infrastructure.jpa.entity;

import com.gangdestrois.smartimmo.domain.filter.prospect.enums.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import jakarta.persistence.*;


@Entity
@Table(name = "prospect_filter")
public class ProspectFilterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prospect_filter")
    private Long id;

    @Column(name = "prospect_filter_name")
    private String prospectFilterName;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_origin")
    private ContactOrigin contactOrigine;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_comparator")
    private MathematicalComparator ageComparator;

    @Temporal(TemporalType.DATE)
    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "profession")
    private Profession profession;

    @Column(name = "authorize_contact_on_social_media")
    private Boolean authorizeContactOnSocialMedia;

    public ProspectFilterEntity(String prospectFilterName, ContactOrigin contactOrigine, Title title,
                                MathematicalComparator ageComparator, Integer age, Profession profession,
                                Boolean authorizeContactOnSocialMedia) {
        this.prospectFilterName = prospectFilterName;
        this.contactOrigine = contactOrigine;
        this.title = title;
        this.ageComparator = ageComparator;
        this.age = age;
        this.profession = profession;
        this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
    }

    public ProspectFilterEntity() {
    }

    public static ProspectFilterEntity fromModelToEntity(ProspectFilter prospectFilter) {
        return new ProspectFilterEntity(
                prospectFilter.getProspectFilterName(), prospectFilter.getContactOrigin(), prospectFilter.getTitle(),
                prospectFilter.getAgeComparator(), prospectFilter.getAge(), prospectFilter.getProfession(),
                prospectFilter.isAuthorizeContactOnSocialMedia()
        );
    }

    public ProspectFilter toModel() {
        return new ProspectFilter(
                this.id, this.prospectFilterName, this.contactOrigine, this.title,
                this.ageComparator, this.age, this.profession,
                this.authorizeContactOnSocialMedia
        );
    }
}
