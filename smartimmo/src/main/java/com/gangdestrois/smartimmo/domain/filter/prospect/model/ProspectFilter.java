package com.gangdestrois.smartimmo.domain.filter.prospect.model;

import com.gangdestrois.smartimmo.domain.filter.prospect.enums.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;

public class ProspectFilter {
    private Long id;
    private final String prospectFilterName;
    private final ContactOrigin contactOrigin;
    private final Title title;
    private final MathematicalComparator ageComparator;
    private final Integer age;
    private final Profession profession;
    private final Boolean authorizeContactOnSocialMedia;

    public ProspectFilter(String prospectFilterName, ContactOrigin contactOrigin, Title title,
                          MathematicalComparator ageComparator, Integer age, Profession profession,
                          Boolean authorizeContactOnSocialMedia) {
        this.prospectFilterName = prospectFilterName;
        this.contactOrigin = contactOrigin;
        this.title = title;
        this.ageComparator = ageComparator;
        this.age = age;
        this.profession = profession;
        this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
    }

    public ProspectFilter(Long id, String prospectFilterName, ContactOrigin contactOrigin, Title title,
                          MathematicalComparator ageComparator, Integer age, Profession profession,
                          Boolean authorizeContactOnSocialMedia) {
        this.id = id;
        this.prospectFilterName = prospectFilterName;
        this.contactOrigin = contactOrigin;
        this.title = title;
        this.ageComparator = ageComparator;
        this.age = age;
        this.profession = profession;
        this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
    }

    public Long getId() {
        return id;
    }

    public String getProspectFilterName() {
        return prospectFilterName;
    }

    public ContactOrigin getContactOrigin() {
        return contactOrigin;
    }

    public Title getTitle() {
        return title;
    }

    public MathematicalComparator getAgeComparator() {
        return ageComparator;
    }

    public Integer getAge() {
        return age;
    }

    public Profession getProfession() {
        return profession;
    }

    public Boolean isAuthorizeContactOnSocialMedia() {
        return authorizeContactOnSocialMedia;
    }
}
