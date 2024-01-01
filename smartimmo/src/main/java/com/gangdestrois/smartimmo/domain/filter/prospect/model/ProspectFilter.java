package com.gangdestrois.smartimmo.domain.filter.prospect.model;

import com.gangdestrois.smartimmo.domain.filter.prospect.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.prospect.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.Profession;
import com.gangdestrois.smartimmo.domain.prospect.Title;

public class ProspectFilter {
    //private Long id;
    private static String prospectFilterName;
    private static ContactOrigin contactOrigin;
    private static Title title;
    private static MathematicalComparator ageComparator;
    private static Integer age;
    private static Profession profession;
    private static Boolean authorizeContactOnSocialMedia;


    public ProspectFilter(String prospectFilterName, ContactOrigin contactOrigin, Title title,
                          MathematicalComparator ageComparator, Integer age, Profession profession,
                          Boolean authorizeContactOnSocialMedia) {
        this.prospectFilterName=prospectFilterName;
        this.contactOrigin = contactOrigin;
        this.title = title;
        this.ageComparator = ageComparator;
        this.age = age;
        this.profession = profession;
        this.authorizeContactOnSocialMedia = authorizeContactOnSocialMedia;
    }

    public static String getProspectFilterName() {
        return prospectFilterName;
    }
    public static ContactOrigin getContactOrigin() {
        return contactOrigin;
    }
    public static Title getTitle() {
        return title;
    }
    public static MathematicalComparator getAgeComparator() {
        return ageComparator;
    }
    public static Integer getAge() {
        return age;
    }
    public static Profession getProfession() {
        return profession;
    }
    public static Boolean isAuthorizeContactOnSocialMedia() {
        return authorizeContactOnSocialMedia;
    }
}
