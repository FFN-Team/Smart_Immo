package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.filter.prospect.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.prospect.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.Profession;
import com.gangdestrois.smartimmo.domain.prospect.Title;
import io.soabase.recordbuilder.core.RecordBuilder;

import static java.util.Objects.nonNull;

@JsonIgnoreProperties()
@RecordBuilder
public record ProspectFilterRequest
(
        String prospectFilterName, ContactOrigin contactOrigin, Title title, MathematicalComparator ageComparator,
        Integer age, Profession profession, Boolean authorizeContactOnSocialMedia
)
{
    public ProspectFilter toModel(){
        return new ProspectFilter(
                this.prospectFilterName,this.contactOrigin, this.title,
                this.ageComparator, this.age, this.profession,
                nonNull(this.authorizeContactOnSocialMedia) ? this.authorizeContactOnSocialMedia : null
                );
    }
}
